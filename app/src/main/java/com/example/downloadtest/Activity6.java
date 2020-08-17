package com.example.downloadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Map;

public class Activity6 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference reference;
    ArrayList<Profile> list;
    RecyclerView recyclerView;
    MyAdapter adapter;
    SearchView searchView;
    TextView textView;
    Spinner spinner;
    TextView textView_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Pop-up
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //
        setContentView(R.layout.activity_6);
        reference = FirebaseDatabase.getInstance().getReference().child("test");
        //reference = FirebaseDatabase.getInstance().getReference().child("Test1"); //124รายการ error
        //reference = FirebaseDatabase.getInstance().getReference().child("Test_real");  //8รายการ
        recyclerView = findViewById(R.id.myRecycler3);

        searchView = findViewById(R.id.searchView);

        //add
        spinner = findViewById(R.id.spinner_in_out);
        textView = findViewById(R.id.txt_count);
        textView_data = findViewById(R.id.txt_data);


        //add spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner_in_out);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.all_in_out_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //String message = getIntent().getStringExtra("month_send");
        //textView.setText(message);
        //show(message);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(reference != null) {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(Profile.class));

                        }
                        //MyAdapter myAdapter = new MyAdapter(list);
                        //recyclerView.setAdapter(myAdapter);
                        //textView.setText("จำนวนรายการทั้งหมด "+ list.size()+" รายการ");
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Activity6.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }

    }

    private void search(String str) {
        ArrayList<Profile> myList = new ArrayList<>();
        String message = getIntent().getStringExtra("month_send");
        textView_data.setText("ข้อมูลบุคคลภายนอกประจำเดือน "+message);
        for (Profile object : list) {
            if (message.contentEquals("มกราคม")){
                if(object.getDate().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("January")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
            else if (message.contentEquals("กุมภาพันธ์")){
                if(object.getDate().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("February")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
            else if (message.contentEquals("มีนาคม")){
                if(object.getDate().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("March")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
            else if (message.contentEquals("เมษายน")){
                if(object.getName().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("April")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
            else if (message.contentEquals("พฤษภาคม")){
                if(object.getDate().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("May")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
            else if (message.contentEquals("มิถุนายน")){
                if(object.getDate().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("June")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
            else if (message.contentEquals("กรกฎาคม")){
                if(object.getDate().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("July")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
            else if (message.contentEquals("สิงหาคม")){
                if(object.getDate().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("August")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
            else if (message.contentEquals("กันยายน")){
                if(object.getDate().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("September")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
            else if (message.contentEquals("ตุลาคม")){
                if(object.getDate().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("October")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
            else if (message.contentEquals("พฤศจิกายน")){
                if(object.getDate().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("November")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
            else if (message.contentEquals("ธันวาคม")){
                if(object.getDate().toLowerCase().contains(str.toLowerCase()) && object.getDate().contains("December")
                        && object.getName().contentEquals("unknown")) {
                    myList.add(object);
                }
            }
        }
        MyAdapter myAdapter = new MyAdapter(myList);
        recyclerView.setAdapter(myAdapter);
        textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
    }


    //String message = getIntent().getStringExtra("month_send");


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ArrayList<Profile> myList = new ArrayList<>();
        String sSelected = parent.getItemAtPosition(position).toString();

        String message = getIntent().getStringExtra("month_send");
        textView_data.setText("ข้อมูลบุคคลภายนอกประจำเดือน "+message);

        if (position != 0){
            Toast.makeText(this, "คุณเลือกเแสดงข้อมูลการ" + sSelected, Toast.LENGTH_SHORT).show();
        }
        //output.settext(parent.getItemAtPosition(position).toString());

        if(message.contentEquals("มกราคม")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("January") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("January")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("January")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }

        else if(message.contentEquals("กุมภาพันธ์")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("February") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("February")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("February")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }

        else if(message.contentEquals("มีนาคม")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("March") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("March")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("March")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }

        else if(message.contentEquals("เมษายน")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("April") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("April")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("April")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }

        else if(message.contentEquals("พฤษภาคม")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("May") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("May")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("May")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }

        else if(message.contentEquals("มิถุนายน")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("June") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("June")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("June")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }

        else if(message.contentEquals("กรกฎาคม")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("July") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("July")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("July")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }

        else if(message.contentEquals("สิงหาคม")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("August") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("August")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("August")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }

        else if(message.contentEquals("กันยายน")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("September") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("September")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("September")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }

        else if(message.contentEquals("ตุลาตม")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("October") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("October")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("October")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }

        else if(message.contentEquals("พฤศจิกายน")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("September") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("September")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("September")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }

        else if(message.contentEquals("ธันวาคม")){
            if (position == 1){
                for (Profile object : list) {
                    if ( object.getDate().contains("December") && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
            if (position == 2) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Entrance") && object.getDate().contains("December")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }

            if (position == 3) {
                for (Profile object : list) {
                    if (object.getStatus().contains("Exit") && object.getDate().contains("December")
                            && object.getName().contentEquals("unknown")) {
                        myList.add(object);
                    }
                }
                MyAdapter myAdapter = new MyAdapter(myList);
                recyclerView.setAdapter(myAdapter);
                textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
            }
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}