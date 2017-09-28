package com.first.shipin1_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
 * Created by gsp on 2017/9/24.
 */

public class NewCounterActivity extends AppCompatActivity {
    public static String filename = "dataStorage.sav";

    private EditText counterNameText;
    private EditText counterInitialValue;
    private EditText counterComment;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_counter);

        Button saveButton = (Button) findViewById(R.id.savebutton);
        counterNameText = (EditText) findViewById(R.id.counterName);
        counterInitialValue = (EditText) findViewById(R.id.initialValue);
        counterComment = (EditText) findViewById(R.id.counterComment);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = counterNameText.getText().toString();
                String comment = counterComment.getText().toString();
                int value = Integer.parseInt(counterInitialValue.getText().toString());

                Counter counter = new Counter(name, value);
                Counters.add(counter);
                saveInFile();
                Toast.makeText(NewCounterActivity.this, "New Counter Saved", Toast.LENGTH_LONG).show();
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();

            }
        });


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
