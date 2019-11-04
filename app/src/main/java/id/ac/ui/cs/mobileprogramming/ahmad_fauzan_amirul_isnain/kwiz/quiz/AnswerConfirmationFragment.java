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
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.QuizContentViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerConfirmationFragment extends Fragment implements AnswerConfirmationInterface {
    private QuizContentViewModel viewModel;

    public static AnswerConfirmationFragment newInstance() {
        return new AnswerConfirmationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAnswerConfirmationBinding binding =
                FragmentAnswerConfirmationBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(getActivity()).get(QuizContentViewModel.class);
        binding.setQuizContentViewModel(viewModel);
        binding.setAnswerConfirmationInterface(this);
        return binding.getRoot();
    }

    @Override
    public void back(){
        QuizActivity quizActivity = (QuizActivity)getActivity();
        quizActivity.back();
    }

    @Override
    public void next(){
        getFragmentManager().beginTransaction()
                .replace(R.id.quizContent, QuizResultFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
