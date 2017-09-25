package com.first.shipin1_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE="com.first.shipin1_countbook.MESSAGE";
    //private ArrayList<Counter> counters = new ArrayList<Counter>();
    //private ArrayAdapter<Counter> adapter;
    //private String[] counterlist = {"food", "water", "equitpment"};
    private ArrayList<String> counterlist = new ArrayList<String>();
    private ListView counterList;
    private TextView totalCounterNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterlist.add("food");
        counterlist.add("water");
        counterlist.add("gear");
        counterlist.add("gun");

        counterList = (ListView) findViewById(R.id.counterList);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, counterlist);
        counterList.setAdapter(adapter);
        TextView totalCounterNumber = (TextView) findViewById(R.id.totalCounter);
        totalCounterNumber.setText("Total number of Counter: " + counterList.getAdapter().getCount());


    }

    public void createCounter(View view){
        Intent intent = new Intent(this, NewCounter.class);
        //intent.putExtra(EXTRA_MESSAGE, );
        startActivity(intent);
    }
}
