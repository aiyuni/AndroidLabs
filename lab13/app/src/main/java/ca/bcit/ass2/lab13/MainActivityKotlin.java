package ca.bcit.ass2.lab13;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.bcit.ass2.lab13.math.Quiz;
import ca.bcit.ass2.lab13.math.game.Addition;
import ca.bcit.ass2.lab13.math.game.Division;
import ca.bcit.ass2.lab13.math.game.Multiplication;
import ca.bcit.ass2.lab13.math.game.Subtraction;

public class MainActivityKotlin extends AppCompatActivity {

    private static final int ADDITION = 0;
    private static final int SUBTRACTION = 1;
    private static final int DIVISION = 2;
    private static final int MULTIPLICATION = 3;

    public static final String CHANNEL_1_ID = "channel1";

    Random random;
    TextView question;
    List<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannels();
        initFloatingActionButton();

        random = new Random(System.currentTimeMillis());
        buttons = new ArrayList<>();

        question = findViewById(R.id.question);
        Button button1 = findViewById(R.id.option1);
        Button button2 = findViewById(R.id.option2);
        Button button3 = findViewById(R.id.option3);
        Button button4 = findViewById(R.id.option4);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);

        doTheQuiz(random);
    }

    private void doTheQuiz(Random random) {
        for (Button button : buttons) {
            button.setOnClickListener(null);
        }
        int quizType = random.nextInt(4);
        Quiz quiz;
        switch (quizType) {
            case ADDITION:
                quiz = new Addition();
                initQuiz(quiz);
                break;
            case SUBTRACTION:
                quiz = new Subtraction();
                initQuiz(quiz);
                break;
            case DIVISION:
                quiz = new Division();
                initQuiz(quiz);
                break;
            case MULTIPLICATION:
                quiz = new Multiplication();
                initQuiz(quiz);
                break;
        }
    }

    private void initFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.SCORE = 0;
                App.TOTAL_QUESTIONS = 0;
                Snackbar.make(view, String.format("Restarting game, resetting score to %s", App.SCORE), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                doTheQuiz(random);
            }
        });
    }

    private void initQuiz(Quiz quiz) {
        String questionText = quiz.getQuestion();
        List<Integer> options = quiz.getOptions();
        int answerIndex = quiz.getAnswerIndex();

        question.setText(questionText);
        for (int i = 0; i < options.size() ; i++) {
            buttons.get(i).setText(String.valueOf(options.get(i)));
        }
        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.SCORE += 0;
                    App.TOTAL_QUESTIONS += 1;
                    if (App.TOTAL_QUESTIONS >= 5) {
                        //finish();
                        startActivity(new Intent(MainActivityKotlin.this, SuccessActivity.class));
                        App.TOTAL_QUESTIONS = 0;
                    }
                    Snackbar.make(view, String.format("You got it wrong, your current score is "+ App.SCORE + " out of " + App.TOTAL_QUESTIONS), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Context app = getApplicationContext().getApplicationContext();
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(app, CHANNEL_1_ID);
                    mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
                    mBuilder.setContentTitle(getResources().getString(R.string.wrong_answer));
                    mBuilder.setContentText("You got it wrong, your current score is "+ App.SCORE + " out of " + App.TOTAL_QUESTIONS);

                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(1, mBuilder.build());


                    doTheQuiz(random);
                }
            });
        }
        buttons.get(answerIndex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.SCORE += 1;
                App.TOTAL_QUESTIONS += 1;
                Snackbar.make(view, String.format("You got it right! Your current score is %s", App.SCORE + " out of " + App.TOTAL_QUESTIONS + " questions asked."), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //SystemClock.sleep(1000);
                Context app = getApplicationContext().getApplicationContext();
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(app, CHANNEL_1_ID);
                mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
                mBuilder.setContentTitle(getResources().getString(R.string.right_answer));
                mBuilder.setContentText("You got it right, your current score is "+ App.SCORE + " out of " + App.TOTAL_QUESTIONS);

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(1, mBuilder.build());

                if (App.TOTAL_QUESTIONS >= 5) {
                    //finish();
                    startActivity(new Intent(MainActivityKotlin.this, SuccessActivity.class));
                    App.TOTAL_QUESTIONS = 0;
                } else {
                    doTheQuiz(random);
                }


            }
        });
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Show scores");

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel1);
        }
    }

}
