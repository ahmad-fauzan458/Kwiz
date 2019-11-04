package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz.TimerAsync;

public class TimerViewModel extends ViewModel {
    private MutableLiveData<Integer> time;
    private MutableLiveData<Boolean> timerFinished;
    private TimerAsync timerAsync;

    public void setTime(Integer time) {
        if (this.time == null) {
            this.time = new MutableLiveData<>();
        }
        this.time.setValue(time);
    }

    public MutableLiveData<Integer> getTime() {
        if (time == null) {
            time = new MutableLiveData<>();
        }
        return time;
    }

    public void setTimerFinished(Boolean timerFinished) {
        if (this.timerFinished == null) {
            this.timerFinished = new MutableLiveData<>();
        }
        this.timerFinished.setValue(timerFinished);
    }

    public MutableLiveData<Boolean> getTimerFinished() {
        if (timerFinished == null){
            timerFinished = new MutableLiveData<>();
        }
        return timerFinished;
    }

    public void setTimerAsync(Integer time) {
        timerAsync = new TimerAsync(this, time);
    }

    public TimerAsync getTimerAsync() {
        return timerAsync;
    }
}
