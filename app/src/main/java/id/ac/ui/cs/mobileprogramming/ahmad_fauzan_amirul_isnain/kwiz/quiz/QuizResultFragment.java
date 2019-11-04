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

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.util.ExternalStoragePermissions;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.databinding.FragmentQuizResultBinding;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.interfaces.QuizResultInterface;

public class QuizResultFragment extends Fragment implements QuizResultInterface {

    private String medalGold;
    private String medalSilver;
    private String medalBronze;

    public static QuizResultFragment newInstance() {
        return new QuizResultFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuizActivity quizActivity = (QuizActivity) getActivity();
        quizActivity.stopTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentQuizResultBinding binding =
                FragmentQuizResultBinding.inflate(inflater, container, false);
        binding.setQuizResultInterface(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ExternalStoragePermissions.verifyStoragePermissions(getActivity());

        medalGold = createImageOnData(R.drawable.medal_gold);
        medalSilver = createImageOnData(R.drawable.medal_silver);
        medalBronze = createImageOnData(R.drawable.medal_bronze);
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
        onShareMedal(medalGold);
    }
}
