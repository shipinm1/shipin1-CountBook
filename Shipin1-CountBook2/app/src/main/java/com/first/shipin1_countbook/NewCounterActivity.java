package com.first.shipin1_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import static com.first.shipin1_countbook.Counter.Counters;

/**
 * Created by shipin1 on 2017/9/24.
 *
 * Version 1.0
 *
 * NewCounterActivity Class Description:
 * functionality of creating new counters.
 * Asking user input data such as:
 * counter name, counter initial value, counter comments.
 * Set the current value of counter equal to initial value by default
 * Auto generate date when creation was completed.
 */

public class NewCounterActivity extends AppCompatActivity {
    public static String filename = "dataStorage.sav";      //file name
    private EditText counterNameText;                       //counter name
    private EditText counterInitialValue;                   //initial value
    private EditText counterComment;                        //counter comment.

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_counter);
        //initialization, preparation
        Button saveButton = (Button) findViewById(R.id.savebutton);
        counterNameText = (EditText) findViewById(R.id.counterName);
        counterInitialValue = (EditText) findViewById(R.id.initialValue);
        counterComment = (EditText) findViewById(R.id.counterComment);

        // save button activity, if statements for error checking
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = counterNameText.getText().toString();
                if (name.matches("")) {
                    Toast.makeText(NewCounterActivity.this, "Name Can Not Be Empty\nCreation Failed", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    String comment = counterComment.getText().toString();
                    String initvalue = counterInitialValue.getText().toString();
                    if (initvalue.matches("")) {
                        counterInitialValue.setText("0");
                    }
                    int value = Integer.parseInt(counterInitialValue.getText().toString());
                    Counter counter = new Counter(name, value, comment);
                    Counters.add(counter);
                    saveInFile();
                    Toast.makeText(NewCounterActivity.this, "New Counter Saved", Toast.LENGTH_LONG).show();
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }

    //save file action by using Gson
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
