package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        QuizHeaderFragment quizHeaderFragment = QuizHeaderFragment.newInstance();
        quizHeaderFragment.setArguments(getIntent().getExtras());

        if (findViewById(R.id.quizHeader)!= null && findViewById(R.id.quizContent)!= null) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.quizHeader, quizHeaderFragment, null)
                        .add(R.id.quizContent, QuizContentFragment.newInstance(), null)
                        .commit();
            }
        }
    }
}
