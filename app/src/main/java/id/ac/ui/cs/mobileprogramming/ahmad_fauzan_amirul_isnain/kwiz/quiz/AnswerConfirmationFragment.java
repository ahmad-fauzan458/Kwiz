package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.databinding.FragmentAnswerConfirmationBinding;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.interfaces.AnswerConfirmationInterface;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.util.LocaleCheck;
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


        if (LocaleCheck.isLocaleIndonesia()){
            noteViewModel.addToFullNote("Question:\n" +
                    questionViewModel.getCurrentIdQuestion().getValue());
        } else {
            noteViewModel.addToFullNote("Question:\n" +
                    questionViewModel.getCurrentEnQuestion().getValue());
        }
        noteViewModel.addToFullNote("Note:\n" +
                noteViewModel.getNote().getValue());

        noteViewModel.setNote(null);
        questionViewModel.nextQuestion();
        optionsViewModel.setOptionChecked(null);

        getFragmentManager().beginTransaction()
                .replace(R.id.quizContent, QuizContentFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
