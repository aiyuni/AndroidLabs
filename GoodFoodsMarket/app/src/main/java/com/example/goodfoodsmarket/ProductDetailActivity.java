package com.example.goodfoodsmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        /*Toast toast = Toast.makeText(getApplicationContext(), "inside product detail!", Toast.LENGTH_LONG);
        toast.show(); */


        Bundle bundle = getIntent().getExtras();
        ImageView imageView = findViewById(R.id.imageView1);
        TextView nameView = findViewById(R.id.textName);
        TextView categoryView = findViewById(R.id.textCategory);
        TextView countryOfOriginView = findViewById(R.id.textCountryOfOrigin);
        TextView unitView = findViewById(R.id.textUnit);
        TextView priceView = findViewById(R.id.textPrice);

        byte[] b = bundle.getByteArray("photo");
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

        imageView.setImageBitmap(bitmap);
        nameView.setText(bundle.getString("name"));
        categoryView.setText(bundle.getString("category"));
        countryOfOriginView.setText(bundle.getString("countryOfOrigin"));
        unitView.setText(bundle.getString("unit"));
        priceView.setText(String.valueOf(bundle.getFloat("price")));
    }
}
