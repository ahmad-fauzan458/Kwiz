package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.dao.QuestionDao;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.database.KwizDatabase;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.models.Question;

public class QuestionRepository {
    private QuestionDao questionDao;

    public QuestionRepository(Application application) {
        KwizDatabase database = KwizDatabase.getInstance(application);
        questionDao = database.questionDao();
    }

    public void insert(Question question) {
        new InsertQuestionAsyncTask(questionDao).execute(question);
    }

    public void update(Question question) {
        new UpdateQuestionAsyncTask(questionDao).execute(question);
    }

    public void delete(Question question) {
        new DeleteQuestionAsyncTask(questionDao).execute(question);
    }

    private static class InsertQuestionAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionDao questionDao;

        private InsertQuestionAsyncTask(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.insert(questions[0]);
            return null;
        }
    }

    private static class UpdateQuestionAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionDao questionDao;

        private UpdateQuestionAsyncTask(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.update(questions[0]);
            return null;
        }
    }

    private static class DeleteQuestionAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionDao questionDao;

        private DeleteQuestionAsyncTask(QuestionDao questionDao) {
            this.questionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.delete(questions[0]);
            return null;
        }
    }

    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }
}
