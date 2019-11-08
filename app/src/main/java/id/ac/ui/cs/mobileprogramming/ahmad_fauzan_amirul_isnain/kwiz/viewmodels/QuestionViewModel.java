package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ListIterator;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.models.Question;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.repositories.QuestionRepository;

public class QuestionViewModel extends AndroidViewModel {
    private MutableLiveData<String> currentQuestion;
    private MutableLiveData<String> currentQuestionOptionA;
    private MutableLiveData<String> currentQuestionOptionB;
    private MutableLiveData<String> currentQuestionOptionC;
    private MutableLiveData<String> currentQuestionOptionD;
    private String currentAnswer;
    private ListIterator<Question> questionsIterator;
    private Boolean questionRunsOut;

    public QuestionViewModel(@NonNull Application application) {
        super(application);
        QuestionRepository questionRepository = new QuestionRepository(application);
        questionsIterator = questionRepository.getAllQuestions().listIterator();
        questionRunsOut = false;
        nextQuestion();
    }

    private void setCurrentQuestion(String currentQuestion) {
        if (this.currentQuestion == null) {
            this.currentQuestion = new MutableLiveData<>();
        }
        this.currentQuestion.setValue(currentQuestion);
    }

    private void setCurrentQuestionOptionA(String optionA) {
        if (this.currentQuestionOptionA == null) {
            this.currentQuestionOptionA = new MutableLiveData<>();
        }
        this.currentQuestionOptionA.setValue(optionA);
    }

    private void setCurrentQuestionOptionB(String optionB) {
        if (this.currentQuestionOptionB == null) {
            this.currentQuestionOptionB = new MutableLiveData<>();
        }
        this.currentQuestionOptionB.setValue(optionB);
    }

    private void setCurrentQuestionOptionC(String optionC) {
        if (this.currentQuestionOptionC == null) {
            this.currentQuestionOptionC = new MutableLiveData<>();
        }
        this.currentQuestionOptionC.setValue(optionC);
    }

    private void setCurrentQuestionOptionD(String optionD) {
        if (this.currentQuestionOptionD == null) {
            this.currentQuestionOptionD = new MutableLiveData<>();
        }
        this.currentQuestionOptionD.setValue(optionD);
    }

    private void setCurrentAnswer(String currentAnswer) {
        this.currentAnswer = currentAnswer;
    }

    private void setQuestionRunsOut(Boolean questionRunsOut) {
        this.questionRunsOut = questionRunsOut;
    }

    public MutableLiveData<String> getCurrentQuestion() {
        if (currentQuestion == null) {
            currentQuestion = new MutableLiveData<>();
        }
        return currentQuestion;
    }

    public MutableLiveData<String> getCurrentQuestionOptionA() {
        if (currentQuestionOptionA == null) {
            currentQuestionOptionA = new MutableLiveData<>();
        }
        return currentQuestionOptionA;
    }

    public MutableLiveData<String> getCurrentQuestionOptionB() {
        if (currentQuestionOptionB == null) {
            currentQuestionOptionB = new MutableLiveData<>();
        }
        return currentQuestionOptionB;
    }

    public MutableLiveData<String> getCurrentQuestionOptionC() {
        if (currentQuestionOptionC == null) {
            currentQuestionOptionC = new MutableLiveData<>();
        }
        return currentQuestionOptionC;
    }

    public MutableLiveData<String> getCurrentQuestionOptionD() {
        if (currentQuestionOptionD == null) {
            currentQuestionOptionD = new MutableLiveData<>();
        }
        return currentQuestionOptionD;
    }

    public String getCurrentAnswer() {
        return currentAnswer;
    }

    public void nextQuestion() {
        if (questionsIterator.hasNext()) {
            Question question = questionsIterator.next();
            setCurrentQuestion(question.getContent());
            setCurrentQuestionOptionA(question.getOptionA());
            setCurrentQuestionOptionB(question.getOptionB());
            setCurrentQuestionOptionC(question.getOptionC());
            setCurrentQuestionOptionD(question.getOptionD());
            setCurrentAnswer(question.getAnswer());
            if (!questionsIterator.hasNext()){
                setQuestionRunsOut(true);
            }
        }
    }

    public Boolean isQuestionRunsOut() {
        return questionRunsOut;
    }
}
