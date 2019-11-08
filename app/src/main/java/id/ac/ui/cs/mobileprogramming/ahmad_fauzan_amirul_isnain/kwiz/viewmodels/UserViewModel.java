package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.models.User;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.repositories.MedalRepository;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private MutableLiveData<String> name;
    private MutableLiveData<Integer> score;
    private UserRepository userRepository;
    private MedalRepository medalRepository;


    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        medalRepository = new MedalRepository(application);
        addScore(0);
    }

    public void setName(String name) {
        if (this.name == null) {
            this.name = new MutableLiveData<>();
        }
        this.name.setValue(name);
    }

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
        }
        return name;
    }

    public void addScore(Integer score) {
        if (this.score == null) {
            this.score = new MutableLiveData<>();
        }

        if (this.score.getValue() == null){
            this.score.setValue(score);
            return;
        }
        
        this.score.setValue(this.score.getValue() + score);
    }

    public MutableLiveData<Integer> getScore() {
        if (score == null) {
            score = new MutableLiveData<>();
        }
        return score;
    }

    public void saveData() {
        userRepository.insert(new User(this.name.getValue(), this.score.getValue(),
                medalRepository.getFirstMedalLessThanScore(this.score.getValue()).getId()));
    }
}
