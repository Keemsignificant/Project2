package com.example.downloadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Activity4 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DatabaseReference reference;
    ArrayList<Profile> list;

    private Button button_all,button_in,button_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        TextView textView = findViewById(R.id.textView_months);
        //Spinner spinner = findViewById(R.id.spinner);

        /*
        ImageView imageView = findViewById(R.id.image_view);
        String url = "https://storage.googleapis.com/login1-b7af5.appspot.com/Keem/5Keem";
        Picasso.get().load(url).into(imageView);

         */

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months_state, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        reference = FirebaseDatabase.getInstance().getReference().child("test");
        //reference = FirebaseDatabase.getInstance().getReference().child("Test1"); //124รายการ error
        //reference = FirebaseDatabase.getInstance().getReference().child("Test_real");  //8รายการ

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(reference != null) {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists())
                    {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(Profile.class));
                        }
                        //count();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Activity4.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

    //Count
/*
    public void count(){
        TextView textView1 = findViewById(R.id.textView_data);
        ArrayList<Profile> Count_all = new ArrayList<>();
        ArrayList<Profile> Count_out = new ArrayList<>();
        for (Profile object : list){
            if (object.getDate().contains("January")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            if (object.getDate().contains("February")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            if (object.getDate().contains("March")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            if (object.getDate().contains("April")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            if (object.getDate().contains("May")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            if (object.getDate().contains("June")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            if (object.getDate().contains("July")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            if (object.getDate().contains("August")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            if (object.getDate().contains("September")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            if (object.getDate().contains("October")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            if (object.getDate().contains("November")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            if (object.getDate().contains("December")){
                Count_all.add(object);
                count_all = Count_all.size();
                if (object.getName().contentEquals("unknown")){
                    Count_out.add(object);
                    count_out = Count_out.size();
                }
            }
            String count_send ="จำนวนบุคคลทั้งหมด "+ count_all +" คน"+
                    "\nบุคลากรจำนวน "+ (count_all-count_out) +" คน"+
                    "\nบุคคลภายนอก "+ count_out +" คน" ;

            textView1.setText("จำนวนบุคคลทั้งหมด "+ count_all +" คน"+
                    "\nบุคลากรจำนวน "+ (count_all-count_out) +" คน"+
                    "\nบุคคลภายนอก "+ count_out +" คน");

            //textView1.setText(count_send);
        }
    }

 */


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ArrayList<Profile> Count_all = new ArrayList<>(); //นับคนที่งนอกใน เข้า-ออก
        ArrayList<Profile> Count_all_exit = new ArrayList<>(); //นับคนนอก-ใน ออก

        ArrayList<Profile> Count_out = new ArrayList<>(); //นับคนนอก
        ArrayList<Profile> Count_out_exit = new ArrayList<>(); //นับคนนอกที่ ออก

        ArrayList<Profile> Count_in_all = new ArrayList<>(); //นับคนในที่ เข้า-ออก
        ArrayList<Profile> Count_in_exit = new ArrayList<>(); //นับคนใน ออก

        TextView textView = findViewById(R.id.textView_months);

        TextView textView_data_all = findViewById(R.id.textView_data_all);
        TextView textView_data_in = findViewById(R.id.textView_data_in);
        TextView textView_data_out = findViewById(R.id.textView_data_out);

        String sSelected = parent.getItemAtPosition(position).toString();
        if (position != 0){
            Toast.makeText(this,sSelected,Toast.LENGTH_SHORT).show();
            textView.setText("ข้อมูลประจำเดือน" + parent.getItemAtPosition(position).toString());
        }

        //textView_data.setText();

        //String count_receive = getIntent().getStringExtra("count_send");
       // textView_data.setText("test\n"+count_receive);

        /* "\nจำนวนบุคคลทั้งหมด\t30 คน"+
                "\nบุคลากรจำนวน\t10 คน"+
                "\nบุคคลภายนอก\t20 คน"
                */
        button_all = findViewById(R.id.bt_check_all);
        button_in = findViewById(R.id.bt_check_in);
        button_out = findViewById(R.id.bt_check_out);

        if (position == 1){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "มกราคม";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "มกราคม";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "มกราคม";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("January")){
                    Count_all.add(object);
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }

            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");

            /*
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  คน");
            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  คน");
            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  คน");

             */

            /*textView_data.setText("จำนวนบุคคลทั้งหมด "+ Count_all.size() +" คน"+
                    "\nบุคลากรจำนวน "+ (Count_all.size() - Count_out.size()) +" คน"+
                    "\nบุคคลภายนอก "+ Count_out.size() +" คน");

             */
        }
        if (position == 2){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "กุมภาพันธ์";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "กุมภาพันธ์";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "กุมภาพันธ์";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("February")){
                    Count_all.add(object);
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");
        }
        if (position == 3){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "มีนาคม";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "มีนาคม";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "มีนาคม";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("March")){
                    Count_all.add(object);
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");
        }
        if (position == 4){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "เมษายน";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "เมษายน";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "เมษายน";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("April")){
                    Count_all.add(object); //all_in_out
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");

        }
        if (position == 5){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "พฤษภาคม";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "พฤษภาคม";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "พฤษภาคม";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("May")){
                    Count_all.add(object);
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");
        }
        if (position == 6){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "มิถุนายน";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "มิถุนายน";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "มิถุนายน";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("June")){
                    Count_all.add(object);
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");
        }
        if (position == 7){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "กรกฎาคม";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "กรกฎาคม";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "กรกฎาคม";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("July")){
                    Count_all.add(object);
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");
        }
        if (position == 8){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "สิงหาคม";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "สิงหาคม";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "สิงหาคม";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("August")){
                    Count_all.add(object);
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");
        }
        if (position == 9){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "กันยายน";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "กันยายน";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "กันยายน";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("September")){
                    Count_all.add(object);
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");
        }
        if (position == 10){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "ตุลาคม";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "ตุลาคม";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "ตุลาคม";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("October")){
                    Count_all.add(object);
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");
        }
        if (position == 11){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "พฤศจิกายน";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "พฤศจิกายน";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "พฤศจิกายน";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("November")){
                    Count_all.add(object);
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");
        }
        if (position == 12){
            button_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "ธันวาคม";
                    Intent intent = new Intent(Activity4.this, Activity3.class);
                    intent.putExtra("month_send",month);
                    startActivity(intent);
                }
            });
            button_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "ธันวาคม";
                    Intent intent2 = new Intent(Activity4.this, Activity5.class);
                    intent2.putExtra("month_send",month);
                    startActivity(intent2);
                }
            });
            button_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = "ธันวาคม";
                    Intent intent3 = new Intent(Activity4.this, Activity6.class);
                    intent3.putExtra("month_send",month);
                    startActivity(intent3);
                }
            });
            for (Profile object : list){
                if (object.getDate().contains("December")){
                    Count_all.add(object);
                    if (object.getName().contentEquals("unknown")){
                        Count_out.add(object);
                        if (object.getStatus().contentEquals("Exit")){
                            Count_out_exit.add(object); //Entrance = all-exit คนนอก
                        }
                    }
                    if (object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                            || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                            || object.getName().contentEquals("Tubtim")){

                        Count_in_all.add(object);

                        if (object.getStatus().contentEquals("Exit")){
                            Count_in_exit.add(object);
                        }
                    }
                    if (object.getStatus().contentEquals("Exit")){
                        Count_all_exit.add(object);
                    }
                }
            }
            textView_data_all.setText("จำนวนบุคคลทั้งหมด  "+ Count_all.size() +"  ครั้ง" +
                    "\nเข้า  "+  (Count_all.size() - Count_all_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_all_exit.size() +"  ครั้ง");

            textView_data_in.setText("บุคลากรจำนวน          "+ (Count_all.size() - Count_out.size()) +"  ครั้ง"+
                    "\nเข้า  "+ (Count_in_all.size() - Count_in_exit.size())  +"  ครั้ง" + "\nออก  "+ Count_in_exit.size() +"  ครั้ง");

            textView_data_out.setText("บุคคลภายนอก          "+ Count_out.size() +"  ครั้ง"+
                    "\nเข้า  "+ (Count_out.size() - Count_out_exit.size()) +"  ครั้ง" + "\nออก  "+ Count_out_exit.size() +"  ครั้ง");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
