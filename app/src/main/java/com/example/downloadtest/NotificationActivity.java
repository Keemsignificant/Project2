package com.example.downloadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months);

        TextView textView = findViewById(R.id.text_view);
        ImageView imageView = findViewById(R.id.img_notification);

        String photo = getIntent().getStringExtra("photo");
        Picasso.get().load(photo).into(imageView);
        //Picasso.get().load(profiles.get(i).getPhoto()).into(myViewHolder.imageView1);
        String message = getIntent().getStringExtra("message1");
        textView.setText(message);

    }
}
