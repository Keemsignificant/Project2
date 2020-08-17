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

public class Activity1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference reference;
    ArrayList<Profile> list;
    RecyclerView recyclerView;
    MyAdapter adapter;
    SearchView searchView;
    RadioGroup mradioGroup;
    RadioButton radioButton;
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
        setContentView(R.layout.activity_1);

        //reference = FirebaseDatabase.getInstance().getReference().child("Test10");
        //reference = FirebaseDatabase.getInstance().getReference().child("Test_In");
        //reference = FirebaseDatabase.getInstance().getReference().child("Test_real"); //8รายการ
        //reference = FirebaseDatabase.getInstance().getReference().child("Test1"); //124 รายการ
        reference = FirebaseDatabase.getInstance().getReference().child("test"); //124 รายการ
        recyclerView = findViewById(R.id.myRecycler1);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //list = new ArrayList<Profile>();
        searchView = findViewById(R.id.searchView);

        //add
        mradioGroup = findViewById(R.id.radio_gr);
        spinner = findViewById(R.id.spinner_in_out);
        textView = findViewById(R.id.txt_count);
        textView_data = findViewById(R.id.txt_data);

        //add spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner_in_out);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.in_out_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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
                        //notification();
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(Profile.class));
                            //notification();
                        }
                        //notification();
                        MyAdapter myAdapter = new MyAdapter(list);
                        notification();
                        recyclerView.setAdapter(myAdapter);
                        //notification();
                        textView.setText("จำนวนรายการทั้งหมด "+ list.size()+" รายการ");
                        //notification();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Activity1.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

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
        for (Profile object : list)
        {
            if(object.getName().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }
        MyAdapter myAdapter = new MyAdapter(myList);
        recyclerView.setAdapter(myAdapter);
        textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
    }

    //add
    private void notification() {
        ArrayList<Profile> myList = new ArrayList<>();
        for (Profile object : list){
            if(myList.add(object)) {
                //section of notification
                //String message = "" + object.getName() + " : " + object.getTime();

                String message1 = ""+object.getStatus()+"\nชื่อ: "+ object.getName()+"\nวัน: "+ object.getDay()+
                        "\nเวลา: "+ object.getTime()+"\nวันที่: "+ object.getDate();

                String message = "" + object.getName() + " : " + object.getTime();
                String photo = ""+ object.getPhoto();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this
                )
                        .setSmallIcon(R.drawable.ic_people)
                        .setContentTitle("ระบบแจ้งเตือน")
                        .setContentText("ข้อมูลการเข้า-ออก ล่าสุด")
                        //.setVibrate(new long[] { 500, 1000, 500 })
                        //pop-up
                        .setVibrate(new long[]{50,50})
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        //
                        .setAutoCancel(true);

                Intent intent = new Intent(Activity1.this, NotificationActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message1",message1);
                intent.putExtra("photo",photo);

                PendingIntent pendingIntent = PendingIntent.getActivity(Activity1.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager)getSystemService(
                        Context.NOTIFICATION_SERVICE
                );

                notificationManager.notify(0,builder.build());
                //

            }
        }
    }

    public void checkButton(View v) {

        int radioId = mradioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        // Toast.makeText(this,"คุณเลือกแสดง : " + radioButton.getText(),Toast.LENGTH_SHORT).show();

        boolean checked = ((RadioButton) v).isChecked();
        ArrayList<Profile> myList = new ArrayList<>();
        switch (v.getId()){
            case R.id.radio_all:
                if(checked) {
                    textView_data.setText("แสดงข้อมูลทั้งหมด");
                    for (Profile object : list)
                    {
                        if(object.getName() != null)
                        {
                            myList.add(object);
                        }
                    }
                    MyAdapter myAdapter = new MyAdapter(myList);
                    recyclerView.setAdapter(myAdapter);
                    textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
                    break;
                }


            case R.id.radio_in:
                if(checked){
                    textView_data.setText("แสดงข้อมูลเฉพาะบุคลากร");
                    for (Profile object : list)
                    {
                        if(object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin")
                                || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")
                                || object.getName().contentEquals("Tubtim"))
                        {
                            myList.add(object);
                        }
                    }
                    MyAdapter myAdapter = new MyAdapter(myList);
                    recyclerView.setAdapter(myAdapter);
                    textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
                    break;
                }
            case R.id.radio_out:
                if(checked){
                    textView_data.setText("แสดงข้อมูลเฉพาะบุคคลภายนอก");
                    for (Profile object : list)
                    {
                        if(object.getName().contentEquals("unknown"))
                        {
                            myList.add(object);
                        }
                    }
                    MyAdapter myAdapter = new MyAdapter(myList);
                    recyclerView.setAdapter(myAdapter);
                    textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
                    break;
                }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ArrayList<Profile> myList = new ArrayList<>();
        String sSelected = parent.getItemAtPosition(position).toString();

        if (position != 0){
            Toast.makeText(this, "คุณเลือกเแสดงข้อมูลการ" + sSelected, Toast.LENGTH_SHORT).show();
        }

        //output.settext(parent.getItemAtPosition(position).toString());

        if (position == 1) {
            textView_data.setText("แสดงข้อมูลการเข้าของบุคคลทั้งหมด");
            for (Profile object : list) {
                if (object.getStatus().contains("Entrance")) {
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }

        if (position == 2) {
            textView_data.setText("แสดงข้อมูลการออกของบุคคลทั้งหมด");
            for (Profile object : list) {
                if (object.getStatus().contains("Exit")) {
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}