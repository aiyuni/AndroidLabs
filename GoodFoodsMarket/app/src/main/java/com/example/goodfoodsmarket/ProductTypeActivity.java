package com.example.goodfoodsmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_type);

        Bundle bundle = getIntent().getExtras();
        String category = bundle.getString("categoryName");

        Food[] categoryFood;

        if (category.equals("Fruit")){
            categoryFood = Food.fruits;
        }
        else if (category.equals("Vegetables")){
            categoryFood = Food.vegetables;
        }
        else if (category.equals("Bakery")){
            categoryFood = Food.bakery;
        }
        else {
            categoryFood = null;
            Toast toast = Toast.makeText(getApplicationContext(), "Something went wrong, no category selected", Toast.LENGTH_LONG);
            toast.show();
        }

        final List<Food> foodsList = new ArrayList<Food>();

        for (int i = 0; i < categoryFood.length; i++){
            foodsList.add(categoryFood[i]);
        }

        ListView view = findViewById(R.id.listType);


        ArrayAdapter<Food> arrayAdapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1, foodsList);
        view.setAdapter(arrayAdapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), foodsList.get(position).getImageResourceId());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();

                Intent intent = new Intent(ProductTypeActivity.this, ProductDetailActivity.class);
                intent.putExtra("name", foodsList.get(position).getName());
                intent.putExtra("category", foodsList.get(position).getCategory());
                intent.putExtra("countryOfOrigin", foodsList.get(position).getCountryOfOrigin());
                intent.putExtra("unit", foodsList.get(position).getUnit());
                intent.putExtra("price", foodsList.get(position).getPrice());
                intent.putExtra("photo", b);
                startActivity(intent);
            }
        });

    }
}
