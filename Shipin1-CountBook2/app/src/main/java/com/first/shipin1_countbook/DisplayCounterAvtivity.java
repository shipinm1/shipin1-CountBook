package com.first.shipin1_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import static com.first.shipin1_countbook.Counter.Counters;
import static com.first.shipin1_countbook.MainActivity.filename;

/**
 * Created by gsp on 2017/9/24.
 */

public class DisplayCounterAvtivity extends AppCompatActivity{
    TextView showValue;
    int countvalue ;
    int countinitial;
    int position;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_activity);

        //ToDo add "save" and "delete" button
        Button saveButton = (Button) findViewById(R.id.CounterSaveButton);
        Button deleteButton = (Button) findViewById(R.id.CounterDeleteButton);
        Bundle bundle = getIntent().getExtras();
        TextView counterName = (TextView) findViewById(R.id.CounterTextView);
        counterName.setText(bundle.getString("CounterName"));

        TextView counterComment = (TextView) findViewById(R.id.counterComment);
        counterComment.setText(bundle.getString("CounterComment"));

        position = (bundle.getInt("position"));
        showValue = (TextView) findViewById(R.id.CountNumberDisplay);
        countvalue = (bundle.getInt("CounterCurrentValue"));
        showValue.setText(Integer.toString(countvalue));
        countinitial = (bundle.getInt("CounterInitialValue"));



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Counters.get(position).setComment();
                //Counters.get(position).setName();
                Counters.get(position).setDate(new Date());
                Counters.get(position).setCurrentValue(countvalue);
                /*System.out.println("Save button output date: " + new Date());
                System.out.println("Save button output number: " + countvalue);
                System.out.println("Save button output date: " + Counters.get(position).getDate());
                System.out.println("Save button output number: " + Counters.get(position).getCurrentValue());*/
                saveInFile();
                Toast.makeText(DisplayCounterAvtivity.this, "Change Saved", Toast.LENGTH_LONG).show();
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();



            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Counters.remove(position);
                saveInFile();
                Toast.makeText(DisplayCounterAvtivity.this, "Counter Deleted", Toast.LENGTH_LONG).show();
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();



            }
        });


    }
    public void countIncrease(View view){
        countvalue ++;
        showValue.setText(Integer.toString(countvalue));
        System.out.println(" " + countvalue);
    }

    public void countDecrease(View view){
        countvalue --;
        showValue.setText(Integer.toString(countvalue));
        System.out.println(" " + countvalue);
    }

    public void countReset(View view){
        countvalue = countinitial;
        showValue.setText(Integer.toString(countvalue));
        System.out.println(" " + countvalue);
    }

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
