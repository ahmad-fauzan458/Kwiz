package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.NetworkChangeReceiver;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;

public class QuizActivity extends AppCompatActivity {

    public static final Integer QUIZ_TIME = 10;

    private NetworkChangeReceiver networkChangeReceiver;
    private TimerViewModel timerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        QuizHeaderFragment quizHeaderFragment = QuizHeaderFragment.newInstance();
        quizHeaderFragment.setArguments(getIntent().getExtras());

        timerViewModel = ViewModelProviders.of(this).get(TimerViewModel.class);
        final Observer<Boolean> timerFinishedObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean newTimerFinished) {
                if (newTimerFinished != null && newTimerFinished) {
                    showQuizResult();
                }
            }
        };
        timerViewModel.getTimerFinished().observe(this, timerFinishedObserver);

        if (findViewById(R.id.quizHeader)!= null && findViewById(R.id.quizContent)!= null) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.quizHeader, quizHeaderFragment, null)
                        .add(R.id.quizContent, QuizContentFragment.newInstance(), null)
                        .commit();
            }
        }
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuizActivity.this);
        alertDialog.setTitle(getResources().getString(R.string.back_button_disabled));
        alertDialog.setMessage(getResources().getString(R.string.back_button_disabled_content));

        alertDialog.setNegativeButton(getResources().getString(R.string.back_to_quiz),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    public void back(){
        super.onBackPressed();
    }

    public void startTimer(){
        if (timerViewModel != null) {
            timerViewModel.setTimerAsync(QuizActivity.QUIZ_TIME);
            timerViewModel.getTimerAsync().execute();
        }
    }

    public  void stopTimer(){
        if (timerViewModel != null
                && timerViewModel.getTimerAsync().getStatus() != AsyncTask.Status.FINISHED){
            timerViewModel.getTimerAsync().cancel(true);
        }
    }

    private void showQuizResult(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.quizContent, QuizResultFragment.newInstance())
                .commit();
    }
}
