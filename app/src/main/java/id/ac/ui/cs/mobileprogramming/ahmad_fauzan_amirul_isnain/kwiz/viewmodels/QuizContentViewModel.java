package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuizContentViewModel extends ViewModel {
    private MutableLiveData<String> note;
    private MutableLiveData<String> answer;

    public void setNote(String note) {
        if (this.note == null) {
            this.note = new MutableLiveData<>();
        }
        this.note.setValue(note);
    }

    public MutableLiveData<String> getNote() {
        if (note == null) {
            note = new MutableLiveData<>();
        }
        return note;
    }

    public void setAnswer(String answer) {
        if (this.answer == null) {
            this.answer = new MutableLiveData<>();
        }
        this.answer.setValue(answer);
    }

    public MutableLiveData<String> getAnswer() {
        if (answer == null) {
            answer = new MutableLiveData<>();
        }
        return answer;
    }
}

