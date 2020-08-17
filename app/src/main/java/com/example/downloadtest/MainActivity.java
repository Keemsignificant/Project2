package com.example.downloadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1,button2,button3,button4,test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        //button4 = findViewById(R.id.button4);
        //button5 = findViewById(R.id.button3);
        //test = findViewById(R.id.test);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });

        /*
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity5();
            }
        });

         */

        /*
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity5();
            }
        });

         */


/*
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity5();
            }
        });

 */


    }

    public void openActivity1(){
        Intent intent1 = new Intent(getApplicationContext(),Activity1.class);
        startActivity(intent1);
    }

    public void openActivity2(){
        Intent intent2 = new Intent(getApplicationContext(),Activity2.class);
        startActivity(intent2);
    }

    public void openActivity4(){
        Intent intent3 = new Intent(getApplicationContext(),Activity4.class);
        startActivity(intent3);
    }

    /*
    public void openActivity5(){
        Intent intent4 = new Intent(getApplicationContext(),Activity5.class);
        startActivity(intent4);
    }

     */



/*
    public void openActivity4(){
        Intent intent4 = new Intent(getApplicationContext(),Activity4.class);
        startActivity(intent4);
    }

 */
/*
    public void openActivity5(){
        Intent intent5 = new Intent(getApplicationContext(),Activity6.class);
        startActivity(intent5);
    }


 */


}
