package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.dao.MedalDao;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.dao.QuestionDao;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.dao.UserDao;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.models.Medal;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.models.Question;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.models.User;

@Database(entities = {Medal.class, Question.class, User.class}, version = 1)
public abstract class KwizDatabase extends RoomDatabase {

    private static KwizDatabase instance;

    public abstract MedalDao medalDao();
    public abstract QuestionDao questionDao();
    public abstract UserDao userDao();

    public static synchronized KwizDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    KwizDatabase.class, "kwiz_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MedalDao medalDao;
        private QuestionDao questionDao;

        private PopulateDbAsyncTask(KwizDatabase db) {
            medalDao = db.medalDao();
            questionDao = db.questionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Populate Medal
            medalDao.insert(new Medal("Gold", R.drawable.medal_gold, 1800));
            medalDao.insert(new Medal("Silver", R.drawable.medal_silver,800));
            medalDao.insert(new Medal("Bronze", R.drawable.medal_bronze,0));

            //Populate Question and Option
            questionDao.insert(
                    new Question(
                    "5 cats eat 5 fishes in 5 minutes. 200 cats eat 200 fishes " +
                            "in how many minutes?",
                            "1 minute",
                            "200 minute",
                            "5 minute",
                            "0 minute",
                            "C"
                    )
            );

            questionDao.insert(
                    new Question(
                            "During which month do people sleep the least?",
                            "December",
                            "October",
                            "February",
                            "January",
                            "C"
                    )
            );

            questionDao.insert(
                    new Question(
                            "How many times does the alphabet a appear from 0-200?",
                            "0",
                            "28",
                            "75",
                            "100",
                            "A"
                    )
            );

            questionDao.insert(
                    new Question(
                            "If you had only one match, and entered a dark room containing " +
                                    "an oil lamp, some newspaper, and some kindling wood, which " +
                                    "would you light first?",
                            "Oil Lamp",
                            "Match",
                            "Wood",
                            "Newspaper",
                            "B"
                    )
            );

            questionDao.insert(
                    new Question(
                            "If there are 12 fish and half of them drown, how many are there?",
                            "6",
                            "10",
                            "3",
                            "12",
                            "D"
                    )
            );

            questionDao.insert(
                    new Question(
                            "How many times can you subtract 10 from 100?",
                            "10",
                            "5",
                            "2",
                            "1",
                            "D"
                    )
            );

            questionDao.insert(
                    new Question(
                            "Which is heavier, 100 pounds of rocks or 100 pounds of feathers?",
                            "Rocks",
                            "Feathers",
                            "No one",
                            "Cottons",
                            "C"
                    )
            );

            questionDao.insert(
                    new Question(
                            "If a doctor gives you 3 pills and tells you to take one " +
                                    "pill every half hour, how long would it take before all the " +
                                    "pills had been taken?",
                            "0.5 hour",
                            "1 hour",
                            "1.5 hour",
                            "2 hour",
                            "B"
                    )
            );

            questionDao.insert(
                    new Question(
                            "Some months have 31 days, others have 30 days. How many have 28 days?",
                            "1",
                            "2",
                            "9",
                            "12",
                            "D"
                    )
            );

            questionDao.insert(
                    new Question(
                            "If you divide 30 by half and add ten, what do you get?",
                            "25",
                            "40",
                            "70",
                            "100",
                            "C"
                    )
            );

            return null;
        }
    }
}
