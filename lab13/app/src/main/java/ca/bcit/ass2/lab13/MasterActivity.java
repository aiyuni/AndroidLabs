package ca.bcit.ass2.lab13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MasterActivity extends AppCompatActivity {

    Button javaButton;
    Button kotlinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        javaButton = findViewById(R.id.javaButton);
        kotlinButton = findViewById(R.id.kotlinButton);

        javaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MasterActivity.this, MainActivityKotlin.class);
                startActivity(i);
            }
        });

        kotlinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MasterActivity.this, MainActivityKotlin.class);
                startActivity(i);
            }
        });
    }
}
