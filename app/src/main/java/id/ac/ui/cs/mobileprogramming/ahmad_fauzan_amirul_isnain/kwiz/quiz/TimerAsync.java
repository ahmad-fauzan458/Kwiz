package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz.QuizContentFragment;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz.QuizHeaderFragment;

public class TimerAsync extends AsyncTask<Void, Integer, Integer> {
    private int time;
    private QuizHeaderFragment quizHeaderFragment;

    public TimerAsync(QuizHeaderFragment quizHeaderFragment, int time) {
        this.time = time;
        this.quizHeaderFragment = quizHeaderFragment;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        while (time > 0) {
            if(isCancelled()){
                return time;
            }
            publishProgress(time);
            sleepSecond();
            time--;
        }
        publishProgress(time);
        return time;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        quizHeaderFragment.setTimerDisplay(values[0]);
    }

    private void sleepSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignore) {}
    }
}
