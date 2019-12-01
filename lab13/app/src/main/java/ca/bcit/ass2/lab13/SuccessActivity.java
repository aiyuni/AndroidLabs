package ca.bcit.ass2.lab13;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        initFloatingActionButton();

        TextView success = findViewById(R.id.success);
        success.setText(String.format("Your score is " + App.SCORE + " out of 5."));
    }

    private void initFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.SCORE = 0;
                Snackbar.make(view, "Restart the game", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                App.SCORE = 0;
                finish();
                startActivity(new Intent(SuccessActivity.this, MainActivityKotlin.class));
            }
        });
    }
}
