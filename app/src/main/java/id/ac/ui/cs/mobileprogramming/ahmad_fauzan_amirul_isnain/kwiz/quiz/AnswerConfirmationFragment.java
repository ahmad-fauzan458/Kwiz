package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.util.Calendar;
import java.util.Date;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.databinding.FragmentAnswerConfirmationBinding;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.interfaces.AnswerConfirmationInterface;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.NoteViewModel;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.OptionsViewModel;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.QuestionViewModel;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.TimerViewModel;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.UserViewModel;

public class AnswerConfirmationFragment extends Fragment implements AnswerConfirmationInterface {
    private NoteViewModel noteViewModel;
    private OptionsViewModel optionsViewModel;
    private QuestionViewModel questionViewModel;
    private UserViewModel userViewModel;
    private TimerViewModel timerViewModel;

    public static AnswerConfirmationFragment newInstance() {
        return new AnswerConfirmationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAnswerConfirmationBinding binding =
                FragmentAnswerConfirmationBinding.inflate(inflater, container, false);
        noteViewModel = ViewModelProviders.of(getActivity()).get(NoteViewModel.class);
        binding.setNoteViewModel(noteViewModel);
        optionsViewModel = ViewModelProviders.of(getActivity()).get(OptionsViewModel.class);
        binding.setOptionsViewModel(optionsViewModel);
        questionViewModel = ViewModelProviders.of(getActivity()).get(QuestionViewModel.class);
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        timerViewModel = ViewModelProviders.of(getActivity()).get(TimerViewModel.class);

        binding.setAnswerConfirmationInterface(this);
        return binding.getRoot();
    }

    @Override
    public void cancel(){
        QuizActivity quizActivity = (QuizActivity)getActivity();
        quizActivity.back();
    }

    @Override
    public void confirm(){
        if (questionViewModel.getCurrentAnswer().
                equals(optionsViewModel.getOptionChecked().getValue())){
            userViewModel.addScore(QuizActivity.CORRECT_SCORE + timerViewModel.getTime().getValue());
        }

        if (questionViewModel.isQuestionRunsOut()){
            getFragmentManager().beginTransaction()
                    .replace(R.id.quizContent, QuizResultFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
            return;
        }

        questionViewModel.nextQuestion();
        optionsViewModel.setOptionChecked(null);

        writeNoteToFile();
        noteViewModel.setNote(null);

        getFragmentManager().beginTransaction()
                .replace(R.id.quizContent, QuizContentFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    public void writeNoteToFile() {
        if (noteViewModel.getNote().getValue() == null ||
                noteViewModel.getNote().getValue().equals("")){
            return;
        }

        Date currentTime = Calendar.getInstance().getTime();
        String path = Environment.getExternalStorageDirectory() + "/KwizNotes" + currentTime.getTime() + ".txt";
        File file = new File(path);
        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println(noteViewModel.getNote());
            pw.flush();
            pw.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
