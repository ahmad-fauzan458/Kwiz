package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.Calendar;
import java.util.Date;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.util.ExternalStoragePermissions;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.databinding.FragmentQuizResultBinding;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.interfaces.QuizResultInterface;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.MedalViewModel;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.NoteViewModel;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.UserViewModel;

public class QuizResultFragment extends Fragment implements QuizResultInterface {

    private UserViewModel userViewModel;
    private MedalViewModel medalViewModel;
    private String goldMedal;
    private String silverMedal;
    private String bronzeMedal;
    private NoteViewModel noteViewModel;

    private int permissionRequestFlag;
    private final int SHARE_MEDAL_FLAG = 0;
    private final int SAVE_NOTES_FLAG = 1;

    public static QuizResultFragment newInstance() {
        return new QuizResultFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuizActivity quizActivity = (QuizActivity) getActivity();
        quizActivity.stopTimer();
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        if (savedInstanceState == null) {
            userViewModel.saveData();
        }
        noteViewModel = ViewModelProviders.of(getActivity()).get(NoteViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentQuizResultBinding binding =
                FragmentQuizResultBinding.inflate(inflater, container, false);
        binding.setQuizResultInterface(this);
        binding.setUserViewModel(userViewModel);
        medalViewModel = ViewModelProviders.of(getActivity()).get(MedalViewModel.class);
        medalViewModel.setMedal(userViewModel.getScore().getValue());
        binding.setMedalViewModel(medalViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Create an image on the phone's SD card to later be able to share it.
     *
     * @param resID resource ID of an image coming from the res folder.
     * @return Return the path of the image that was created on the phone SD card.
     */
    private String createImageOnData(int resID) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resID);
        String path = Environment.getExternalStorageDirectory() + "/" + resID + ".jpg";
        File file = new File(path);
        try {
            OutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file.getPath();
    }

    private void onShareMedal(String medal) {
        Uri path = FileProvider.getUriForFile(getActivity(),
                "com.id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.fileprovider",
                new File(medal));

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.got_medal));
        shareIntent.putExtra(Intent.EXTRA_STREAM, path);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, getResources().getString(R.string.share)));
    }

    @Override
    public void home(){
        getActivity().finish();
    }

    @Override
    public void share(){
        if (!ExternalStoragePermissions.isPermissionStorageGranted(getActivity())) {
            permissionRequestFlag = SHARE_MEDAL_FLAG;
            ExternalStoragePermissions.requestStoragePermission(this);
            return;
        }

        onShareMedal(getMedal());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ExternalStoragePermissions.isPermissionStorageGranted(getActivity())){
            if (permissionRequestFlag == SHARE_MEDAL_FLAG) {
                onShareMedal(getMedal());
            } else if (permissionRequestFlag == SAVE_NOTES_FLAG) {
                saveNotes();
            }
        } else {
            getFragmentManager().beginTransaction()
                    .replace(R.id.quizContent, PermissionExplanationFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
    }

    private String getMedal() {
        String medal;
        if (medalViewModel.getName().getValue().equals(QuizActivity.GOLD_MEDAL)) {
            medal = (goldMedal == null) ?
                    goldMedal = createImageOnData(R.drawable.medal_gold) : goldMedal;
        } else if (medalViewModel.getName().getValue().equals(QuizActivity.SILVER_MEDAL)) {
            medal = (silverMedal == null) ?
                    silverMedal = createImageOnData(R.drawable.medal_silver) : silverMedal;
        } else {
            medal = (bronzeMedal == null) ?
                    bronzeMedal = createImageOnData(R.drawable.medal_bronze) : bronzeMedal;
        }
        return medal;
    }

    public void saveNotes() {
        if (!ExternalStoragePermissions.isPermissionStorageGranted(getActivity())) {
            permissionRequestFlag = SAVE_NOTES_FLAG;
            ExternalStoragePermissions.requestStoragePermission(this);
            return;
        }

        writeNotesToFile();
        Toast.makeText(getActivity(),
                getActivity().getResources().getString(R.string.save_notes_success), Toast.LENGTH_SHORT)
                .show();
    }

    private void writeNotesToFile() {
        if (noteViewModel.getFullNote().getValue() == null ||
                noteViewModel.getFullNote().getValue().equals("")){
            return;
        }

        Date currentTime = Calendar.getInstance().getTime();
        String path = Environment.getExternalStorageDirectory() + "/KwizNotes" + currentTime.getTime() + ".txt";
        File file = new File(path);
        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println(noteViewModel.getFullNote().getValue());
            pw.flush();
            pw.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
