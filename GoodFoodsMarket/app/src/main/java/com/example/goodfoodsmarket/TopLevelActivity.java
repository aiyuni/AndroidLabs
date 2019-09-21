package com.example.goodfoodsmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;
public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListViews();
    }

    private void setListViews() {
        ListView view = findViewById(R.id.mainListView);
        final List<String> categoryList = new ArrayList<>(Arrays.asList(Food.categories));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoryList);
        view.setAdapter(arrayAdapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, ProductTypeActivity.class);
                intent.putExtra("categoryName", categoryList.get(position));
                startActivity(intent);
            }
        });
    }
}
