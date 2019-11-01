package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.TimerAsync;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizHeaderFragment extends Fragment {

    private final int TIME = 10;

    private TextView usernameTextView;
    private TextView timerTextView;
    private TimerAsync timerAsync;

    public static QuizHeaderFragment newInstance() {
        return new QuizHeaderFragment();
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

        Intent intent = getActivity().getIntent();
        usernameTextView = getView().findViewById(R.id.usernameTextView);
        String namePointer = getContext().getString(R.string.name_pointer);
        usernameTextView.setText(String.format("%s %s", namePointer,
                intent.getStringExtra("username")));

        timerTextView = getView().findViewById(R.id.timerTextView);
        timerAsync = new TimerAsync(this, TIME);
        timerAsync.execute();
    }

    public void setTimerDisplay(int i){
        timerTextView.setText(String.format("%s %s",
                getResources().getString(R.string.time_pointer),i));
    }

    @Override
    public void onDestroy() {
        if (timerAsync != null && timerAsync.getStatus() != AsyncTask.Status.FINISHED){
            timerAsync.cancel(true);
        }
        super.onDestroy();
    }
}
