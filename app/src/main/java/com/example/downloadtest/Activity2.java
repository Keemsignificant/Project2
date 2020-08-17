package com.example.downloadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference reference;
    ArrayList<Profile> list;
    RecyclerView recyclerView;
    MyAdapter adapter;
    SearchView searchView;
    RadioGroup mradioGroup;
    RadioButton radioButton;
    Spinner spinner;
    TextView textView;
    TextView textView_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        reference = FirebaseDatabase.getInstance().getReference().child("test"); //124 รายการ

        //reference = FirebaseDatabase.getInstance().getReference().child("Test1"); //124รายการ error
        //reference = FirebaseDatabase.getInstance().getReference().child("Peoples_Pi_Out_last");
        //reference = FirebaseDatabase.getInstance().getReference().child("Test_real"); //8รายการ
        recyclerView = findViewById(R.id.myRecycler2);

        searchView = findViewById(R.id.searchView);

        //add
        mradioGroup = findViewById(R.id.radio_gr);

        spinner = findViewById(R.id.spinner);

        textView = findViewById(R.id.txt_count);
        textView_data = findViewById(R.id.txt_data);

        //String[] months = {"มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};

        /*
        Intent _inboundIndex = getIntent();
        String _data = _inboundIndex.getStringExtra("data");
        Toast.makeText(getApplicationContext(),_data,Toast.LENGTH_SHORT).show();

         */

        //add spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
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
                    if (dataSnapshot.exists())
                    {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(Profile.class));
                        }
                        MyAdapter myAdapter = new MyAdapter(list);
                        //recyclerView.setAdapter(myAdapter);
                        textView.setText("จำนวนรายการทั้งหมด "+ list.size()+" รายการ");
                        //count();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Activity2.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

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

        //addddd


    }

    private void search(String str)
    {
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
    /*
    public void checkButton(View v)
    {

        int radioId = mradioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        Toast.makeText(this,"คุณเลือกแสดง : " + radioButton.getText(),Toast.LENGTH_SHORT).show();
        boolean checked = ((RadioButton) v).isChecked();

        ArrayList<Profile> myList = new ArrayList<>();

        switch (v.getId()){
            case R.id.radio_all:
                if(checked) {
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
                    for (Profile object : list)
                    {
                        if(object.getName().contentEquals("Keem") || object.getName().contentEquals("Pin") || object.getName().contentEquals("Poe")|| object.getName().contentEquals("Pear")|| object.getName().contentEquals("Tubtim"))
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

     */

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //int radioId = mradioGroup.getCheckedRadioButtonId();
       // radioButton = findViewById(radioId);
        //boolean checked = ((RadioButton) view).isChecked();

        ArrayList<Profile> myList = new ArrayList<>();
        String sSelected = parent.getItemAtPosition(position).toString();
        if (position != 0){
            Toast.makeText(this,"คุณเลือกเแสดง : "+sSelected,Toast.LENGTH_SHORT).show();
        }
        //output.settext(parent.getItemAtPosition(position).toString());
        //checkButton(view.findViewById(radioId));

        if (position == 1){
            textView_data.setText("ข้อมูลทั้งหมด");
            for (Profile object : list){
                if(object.getName() != null){
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }

        if (position == 2){
            textView_data.setText("ข้อมูลประจำเดือน มกราคม");
            for (Profile object : list){
                if(object.getDate().contains("January")){
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");

        }
        if (position == 3){
            textView_data.setText("ข้อมูลประจำเดือน กุมภาพันธ์");
            for (Profile object : list){
                if(object.getDate().contains("February")){
                    myList.add(object);
                }

            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }
        if (position == 4){
            textView_data.setText("ข้อมูลประจำเดือน มีนาคม");
            for (Profile object : list){
                if(object.getDate().contains("March")){
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }
        if (position == 5){
            textView_data.setText("ข้อมูลประจำเดือน เมษายน");
            for (Profile object : list){
                if(object.getDate().contains("April")){
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }
        if (position == 6){
            textView_data.setText("ข้อมูลประจำเดือน พฤษภาคม");
            for (Profile object : list){
                if(object.getDate().contains("May")){
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }
        if (position == 7){
            textView_data.setText("ข้อมูลประจำเดือน มิถุนายน");
            for (Profile object : list){
                if(object.getDate().contains("June")){
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }
        if (position == 8){
            textView_data.setText("ข้อมูลประจำเดือน กรกฎาคม");
            for (Profile object : list){
                if(object.getDate().contains("July")){
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }
        if (position == 9){
            textView_data.setText("ข้อมูลประจำเดือน สิงหาคม");
            for (Profile object : list){
                if(object.getDate().contains("August")){
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }
        if (position == 10){
            textView_data.setText("ข้อมูลประจำเดือน กันยายน");
            for (Profile object : list){
                if(object.getDate().contains("September")){
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }
        if (position == 11){
            textView_data.setText("ข้อมูลประจำเดือน ตุลาคม");
            for (Profile object : list){
                if(object.getDate().contains("October")){
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }
        if (position == 12){
            textView_data.setText("ข้อมูลประจำเดือน พฤศจิกายน");
            for (Profile object : list){
                if(object.getDate().contains("November")){
                    myList.add(object);
                }
            }
            MyAdapter myAdapter = new MyAdapter(myList);
            recyclerView.setAdapter(myAdapter);
            textView.setText("จำนวนรายการทั้งหมด "+ myList.size()+" รายการ");
        }
        if (position == 13){
            textView_data.setText("ข้อมูลประจำเดือน ธันวาคม");
            for (Profile object : list){
                if(object.getDate().contains("December")){
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
