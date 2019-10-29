package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import androidx.lifecycle.ViewModel;

public class QuizContentViewModel extends ViewModel {
    private String note;
    private String answer;

    public void setNote(String note){
        this.note = note;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getNote() {
        return note;
    }

    public String getAnswer() {
        return answer;
    }
}

