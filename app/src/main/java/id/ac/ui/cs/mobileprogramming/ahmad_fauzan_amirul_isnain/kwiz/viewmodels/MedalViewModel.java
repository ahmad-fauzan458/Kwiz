package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.repositories.MedalRepository;

public class MedalViewModel extends AndroidViewModel {
    private MedalRepository medalRepository;
    private MutableLiveData<String> name;

    public MedalViewModel(@NonNull Application application) {
        super(application);
        medalRepository = new MedalRepository(application);
    }

    public void setMedal(int score) {
        if (this.name == null) {
            this.name = new MutableLiveData<>();
        }
        this.name.setValue(medalRepository.getFirstMedalLessThanScore(score).getName());
    }

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
        }
        return name;
    }
}
