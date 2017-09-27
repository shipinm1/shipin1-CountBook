package com.first.shipin1_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String filename = "dataStorage.sav";

    //public static final String EXTRA_MESSAGE="com.first.shipin1_countbook.MESSAGE";
    //private ArrayList<Counter> counters = new ArrayList<Counter>();
    //private ArrayAdapter<Counter> adapter;
    //private String[] counterlist = {"food", "water", "equitpment"};

    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> counterAdapter;


    //below is hard coded array list
    private ArrayList<String> counterlist = new ArrayList<String>();
    private ListView counterList;
    private TextView totalCounterNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date date = new Date();
        counterlist.add("food"+"\nModified on"+date);
        counterlist.add("water"+"\nModified on"+date);
        counterlist.add("gear"+"\nModified on"+date);
        counterlist.add("gun"+"\nModified on"+date);
        counterlist.add("sun"+"\nModified on"+date);

        counterList = (ListView) findViewById(R.id.counterList);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, counterlist);
        counterList.setAdapter(adapter);
        totalCounterNumber = (TextView) findViewById(R.id.totalCounter);
        totalCounterNumber.setText("Total number of Counter: " + counterList.getAdapter().getCount());

        counterList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view, int pos, long arg3){
                Intent intent = new Intent(MainActivity.this, DisplayCounter.class);
                intent.putExtra("CounterName", counterList.getItemAtPosition(pos).toString());
                    startActivity(intent);
            }
        });

    }

    public void createCounter(View view){
        Intent intent = new Intent(this, NewCounter.class);
        //intent.putExtra(EXTRA_MESSAGE, );
        startActivity(intent);
    }

    protected void onStart(){
        super.onStart();
        loadFromFile();
        counterAdapter = new ArrayAdapter<Counter>(this, android.R.layout.simple_list_item_1, counters);
    }




    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            counters = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            counters = new ArrayList<Counter>();
        }
    }


    // probably dont need this part.
    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters,writer);
            writer.flush();
            fos.close();

        }catch (FileNotFoundException e){
            throw new RuntimeException();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
}
