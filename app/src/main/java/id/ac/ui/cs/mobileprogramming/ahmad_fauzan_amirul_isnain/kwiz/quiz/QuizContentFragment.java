package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.databinding.FragmentQuizContentBinding;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.interfaces.QuizContentInterface;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.util.ExternalStoragePermissions;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.NoteViewModel;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.OptionsViewModel;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.QuestionViewModel;

public class QuizContentFragment extends Fragment implements QuizContentInterface {

    private NoteViewModel noteViewModel;
    private OptionsViewModel optionsViewModel;
    private QuestionViewModel questionViewModel;

    public static QuizContentFragment newInstance() {
        return new QuizContentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentQuizContentBinding binding =
                FragmentQuizContentBinding.inflate(inflater, container, false);
        noteViewModel = ViewModelProviders.of(getActivity()).get(NoteViewModel.class);
        binding.setNoteViewModel(noteViewModel);
        optionsViewModel = ViewModelProviders.of(getActivity()).get(OptionsViewModel.class);
        binding.setOptionsViewModel(optionsViewModel);
        questionViewModel = ViewModelProviders.of(getActivity()).get(QuestionViewModel.class);
        binding.setQuestionViewModel(questionViewModel);
        binding.setLifecycleOwner(this);
        binding.setQuizContentInterface(this);
        return binding.getRoot();
    }

    @Override
    public void submit() {
        if (optionsViewModel.getOptionChecked().getValue() == null){
            showErrorAnswerNotChosen();
            return;
        }

        if (noteViewModel.getNote().getValue() != null
                && !noteViewModel.getNote().getValue().equals("")
                && !ExternalStoragePermissions.isPermissionStorageGranted(getActivity())){
            showErrorNoPermissionToSaveNote();
            ExternalStoragePermissions.requestStoragePermission(getActivity());
            return;
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.quizContent, AnswerConfirmationFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    private void showErrorAnswerNotChosen(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle(getResources().getString(R.string.no_answer));
        alertDialog.setMessage(getResources().getString(R.string.no_answer_content));

        alertDialog.setNegativeButton(getResources().getString(R.string.back_to_quiz),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    public void showErrorNoPermissionToSaveNote(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getResources().getString(R.string.write_note_permission));
        alertDialog.setMessage(getResources().getString(R.string.write_note_permission_content));

        alertDialog.setNegativeButton(getResources().getString(R.string.back_to_quiz),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
}
