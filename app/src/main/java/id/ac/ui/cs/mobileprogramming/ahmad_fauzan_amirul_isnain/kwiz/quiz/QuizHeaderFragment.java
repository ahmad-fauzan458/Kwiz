package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.TimerViewModel;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.UserViewModel;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.databinding.FragmentQuizHeaderBinding;

public class QuizHeaderFragment extends Fragment {

    private TimerViewModel timerViewModel;
    private UserViewModel userViewModel;

    public static QuizHeaderFragment newInstance() {
        return new QuizHeaderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        QuizActivity quizActivity = (QuizActivity) getActivity();
        quizActivity.startTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentQuizHeaderBinding binding =
                FragmentQuizHeaderBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        timerViewModel = ViewModelProviders.of(getActivity()).get(TimerViewModel.class);
        binding.setTimerViewModel(timerViewModel);
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        binding.setUserViewModel(userViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        QuizActivity quizActivity= (QuizActivity) getActivity();
        quizActivity.stopTimer();
        super.onDestroy();
    }
}
