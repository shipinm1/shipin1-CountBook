package com.first.shipin1_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import static com.first.shipin1_countbook.Counter.Counters;

/**
 * Created by shipin1 on 2017/9/24.
 *
 * Version 1.0
 *
 * MainActivity Class Description:
 * App start up page
 * Display listView of Counters
 * Call other class such as DisplayCounterActivity and NewCounterActivity
 * information passed by using "intnet,putExtra()"
 */

public class MainActivity extends AppCompatActivity {
    public static final String filename = "dataStorage.sav";        //file name for storing data
    private ListView counterList;                                   //display counter list
    private TextView totalCounterNumber;                            //total counter number


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization, loading from file, preparation.
        loadFromFile();
        Button clearButton = (Button) findViewById(R.id.deleteAll);
        counterList = (ListView) findViewById(R.id.counterList);
        final ArrayAdapter adapter = new ArrayAdapter<Counter>(this, android.R.layout.simple_list_item_1, Counters);
        counterList.setAdapter(adapter);
        totalCounterNumber = (TextView) findViewById(R.id.totalCounter);
        totalCounterNumber.setText("Total number of Counter: " + counterList.getAdapter().getCount());

        //"Delete All" button action
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Counters.clear();
                saveInFile();
                totalCounterNumber.setText("Total number of Counter: " + counterList.getAdapter().getCount());
                adapter.notifyDataSetChanged();
        }
        });

        //viewList click action. passing value call the other activity.
        counterList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view, int pos, long arg3){
                Intent intent = new Intent(MainActivity.this, DisplayCounterAvtivity.class);
                intent.putExtra("CounterName", Counters.get(pos).getName());
                intent.putExtra("CounterDate", Counters.get(pos).getDate());
                intent.putExtra("CounterInitialValue", Counters.get(pos).getInitValue());
                intent.putExtra("CounterCurrentValue", Counters.get(pos).getCurrentValue());
                intent.putExtra("CounterComment", Counters.get(pos).getComment());
                intent.putExtra("position", pos);
                startActivityForResult(intent,1);
            }
        });

    }

    public void createCounter(View view){
        Intent intent = new Intent(this, NewCounterActivity.class);
        startActivityForResult(intent,1);
    }
    protected void onStart(){
        super.onStart();
    }

    //Main page refreshing purpose
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                loadFromFile();
                counterList = (ListView) findViewById(R.id.counterList);
                ArrayAdapter adapter = new ArrayAdapter<Counter>(this, android.R.layout.simple_list_item_1, Counters);
                counterList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                totalCounterNumber.setText("Total number of Counter: " + counterList.getAdapter().getCount());
            }
        }
    }

    //loading file action using Gson
    public void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            Counters = gson.fromJson(in,listType);
        }catch (FileNotFoundException e){
            Counters = new ArrayList<Counter>();
        }
    }

    //saving file action using Gson
    public void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(Counters,writer);
            writer.flush();
            fos.close();

        }catch (FileNotFoundException e){
            throw new RuntimeException();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
}
