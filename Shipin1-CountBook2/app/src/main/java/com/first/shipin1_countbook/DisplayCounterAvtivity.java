package com.first.shipin1_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by shipin1 on 2017/9/24.
 *
 * Version 1.0
 *
 * DisplayCounterActivity Class Description:
 * Handling the functionality of the "Detailed" page of each counter.
 * Used to modify/save/delete the existing counter.
 * class activate when counter was clicked on the main page.
 * information passed by using "intnet,putExtra()"
 */

public class DisplayCounterAvtivity extends AppCompatActivity{
    private TextView showValue;                     //TextView for showing the counter value
    private EditText editedComment;                 //User input comment
    private EditText editedName;                    //User input counter name
    private EditText editedInitialValue;            //User input initial value
    private int countvalue ;                        //count value holder
    private int countinitial;                       //count initial holder
    private int position;                           //counter position in arraylist
    private int temp;                               //temp for storing the temp current value

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_activity);
        // preparation for data receiving and displaying
        editedInitialValue = (EditText) findViewById(R.id.InitialValueView);
        editedComment = (EditText) findViewById(R.id.counterComment);
        editedName = (EditText) findViewById(R.id.CounterTextView);

        Button saveButton = (Button) findViewById(R.id.CounterSaveButton);
        Button deleteButton = (Button) findViewById(R.id.CounterDeleteButton);
        Bundle bundle = getIntent().getExtras();

        TextView counterName = (TextView) findViewById(R.id.CounterTextView);
        counterName.setText(bundle.getString("CounterName"));
        TextView counterComment = (TextView) findViewById(R.id.counterComment);
        counterComment.setText(bundle.getString("CounterComment"));

        showValue = (TextView) findViewById(R.id.CountNumberDisplay);
        position = (bundle.getInt("position"));
        countvalue = (bundle.getInt("CounterCurrentValue"));
        countinitial = (bundle.getInt("CounterInitialValue"));
        temp = countvalue;
        editedInitialValue.setText(Integer.toString(countinitial));
        showValue.setText(Integer.toString(countvalue));

        //Save button action when clicked. If statements for error checking.
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String initvalue = editedInitialValue.getText().toString();
                if (initvalue.matches("")) {
                    editedInitialValue.setText("0");
                }
                int edInitialValue = Integer.parseInt(editedInitialValue.getText().toString());
                String edComment = editedComment.getText().toString();
                String edName = editedName.getText().toString();
                if (edInitialValue < 0){
                    Toast.makeText(DisplayCounterAvtivity.this, "Initial Value Can Not Be Negative\n Change Unsaved", Toast.LENGTH_LONG).show();
                    finish();
                }
                if (countvalue < 0){
                    countvalue = temp;
                    Toast.makeText(DisplayCounterAvtivity.this, "Counter Can Not Be Negative\n Change Unsaved", Toast.LENGTH_LONG).show();
                    finish();
                }
                if (edName.matches("")){
                    Toast.makeText(DisplayCounterAvtivity.this, "Name Can Not Be Empty\n Change Unsaved", Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Counters.get(position).setInitValue(edInitialValue);
                    Counters.get(position).setComment(edComment);
                    Counters.get(position).setName(edName);
                    Counters.get(position).setDate(new Date());
                    Counters.get(position).setCurrentValue(countvalue);
                    saveInFile();
                    Toast.makeText(DisplayCounterAvtivity.this, "Change Saved", Toast.LENGTH_LONG).show();
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
        //delete button actions.
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
    //count up, count down and reset.
    public void countIncrease(View view){
        countvalue ++;
        showValue.setText(Integer.toString(countvalue));
    }

    public void countDecrease(View view){
        countvalue --;
        showValue.setText(Integer.toString(countvalue));
    }

    public void countReset(View view){
        countvalue = countinitial;
        showValue.setText(Integer.toString(countvalue));
    }
    //save file action using Gson serialization
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
