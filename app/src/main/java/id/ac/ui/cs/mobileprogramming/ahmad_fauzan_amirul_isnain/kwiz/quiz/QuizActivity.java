package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.NetworkChangeReceiver;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;

public class QuizActivity extends AppCompatActivity {

    private NetworkChangeReceiver networkChangeReceiver;

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

    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuizActivity.this);
        alertDialog.setTitle(getResources().getString(R.string.back_button_disabled));
        alertDialog.setMessage(getResources().getString(R.string.back_button_disabled_content));

        alertDialog.setNegativeButton(getResources().getString(R.string.back_to_quiz),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    public void back(){
        super.onBackPressed();
    }
}
