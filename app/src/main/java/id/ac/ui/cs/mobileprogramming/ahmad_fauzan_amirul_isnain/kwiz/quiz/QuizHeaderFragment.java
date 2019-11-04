package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizHeaderFragment extends Fragment {

    private TextView usernameTextView;
    private TextView timerTextView;
    private Integer timeRemain;
    private String username;
    private TimerViewModel timerViewModel;

    public static QuizHeaderFragment newInstance() {
        return new QuizHeaderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        QuizActivity quizActivity = (QuizActivity) getActivity();

        timerViewModel = ViewModelProviders.of(quizActivity).get(TimerViewModel.class);
        final Observer<Integer> timeObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newTime) {
                setTimerDisplay(newTime);
            }
        };
        timerViewModel.getTime().observe(this, timeObserver);

        quizActivity.startTimer();

        Intent intent = getActivity().getIntent();
        username = intent.getStringExtra("username");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_header, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setRetainInstance(true);

        usernameTextView = getView().findViewById(R.id.usernameTextView);
        String namePointer = getContext().getString(R.string.name_pointer);
        usernameTextView.setText(String.format("%s %s", namePointer, username));

        timerTextView = getView().findViewById(R.id.timerTextView);
        setTimerDisplay(timeRemain);
    }

    private void setTimerDisplay(Integer i){
        timeRemain = i;
        timerTextView.setText(String.format("%s %s",
                getResources().getString(R.string.time_pointer),timeRemain));
    }

    @Override
    public void onDestroy() {
        QuizActivity quizActivity= (QuizActivity) getActivity();
        quizActivity.stopTimer();
        super.onDestroy();
    }
}
