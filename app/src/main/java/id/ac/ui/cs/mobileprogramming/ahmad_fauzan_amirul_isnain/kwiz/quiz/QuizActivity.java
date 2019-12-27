package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.TimerService;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.TimerViewModel;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.UserViewModel;

public class QuizActivity extends AppCompatActivity {

    public static final int QUIZ_TIME_MS = 100000;
    public static final int CORRECT_SCORE = 100;
    public static final String GOLD_MEDAL = "Gold";
    public static final String SILVER_MEDAL = "Silver";
    public static final String BRONZE_MEDAL = "Bronze";
    private static final int TIMER_NOTIFICATION_ID = 1;

    private TimerViewModel timerViewModel;
    private UserViewModel userViewModel;
    private boolean isForeground;

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int time = intent.getExtras().getInt("countdown");
            NotificationManagerCompat notificationManager
                    = NotificationManagerCompat.from(getApplicationContext());

            timerViewModel.setTime(time);

            if (time == 0) {
                timerViewModel.setTimerFinished(true);
                if (!isForeground) {
                    notificationManager.notify(TIMER_NOTIFICATION_ID,
                            createNotification("Time's up").build());
                }
                return;
            }

            if (!isForeground) {
                notificationManager.notify(TIMER_NOTIFICATION_ID,
                        createNotification("Remaining time: " + time).build());
            }
        }

        private NotificationCompat.Builder createNotification(String contentText) {
            return new NotificationCompat
                    .Builder(getApplicationContext(), "Timer")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Quiz time")
                    .setContentText(contentText)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setOngoing(true)
                    .setOnlyAlertOnce(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        DataBindingUtil.setContentView(this, R.layout.activity_quiz);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        String username = getIntent().getStringExtra("username");
        userViewModel.setName(username == null ? "" : username);

        timerViewModel = ViewModelProviders.of(this).get(TimerViewModel.class);
        final Observer<Boolean> timerFinishedObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean newTimerFinished) {
                if (newTimerFinished != null && newTimerFinished) {
                    showQuizResult();
                }
            }
        };
        timerViewModel.getTimerFinished().observe(this, timerFinishedObserver);

        registerReceiver(br, new IntentFilter(TimerService.TIMER_BR));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.quizHeader, QuizHeaderFragment.newInstance(), null)
                    .add(R.id.quizContent, QuizContentFragment.newInstance(), null)
                    .commit();
        }
    }

    @Override
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

    public void startTimer(){
        Intent intent = new Intent(this, TimerService.class);
        intent.putExtra("quizTime", QUIZ_TIME_MS);
        startService(intent);
    }

    public  void stopTimer(){
        Intent intent = new Intent(this, TimerService.class);
        stopService(intent);
    }

    private void showQuizResult(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.quizContent, QuizResultFragment.newInstance())
                .commit();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(br);
        super.onDestroy();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel("Timer", name, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
        NotificationManagerCompat.from(getApplicationContext()).cancel(TIMER_NOTIFICATION_ID);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isForeground = false;
    }
}
