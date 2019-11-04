package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import android.os.AsyncTask;

import java.util.concurrent.TimeUnit;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.TimerViewModel;

public class TimerAsync extends AsyncTask<Void, Integer, Integer> {
    private Integer time;
    private TimerViewModel timerViewModel;

    public TimerAsync(TimerViewModel timerViewModel, Integer time) {
        this.time = time;
        this.timerViewModel = timerViewModel;
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
        timerViewModel.setTime(values[0]);
    }

    private void sleepSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignore) {}
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        timerViewModel.setTimerFinished(true);
    }
}
