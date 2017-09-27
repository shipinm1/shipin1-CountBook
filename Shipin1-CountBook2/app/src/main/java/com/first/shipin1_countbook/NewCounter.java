package com.first.shipin1_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by gsp on 2017/9/24.
 */

public class NewCounter extends AppCompatActivity{
    public static final String filename = "dataStorage.sav";

    public void onCreate(Bundle savedInstanceState){
        Button saveButton = (Button) findViewById(R.id.savebutton);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_counter);
        //Intent intent = getIntent();
        saveButton.setOnClickListener(new View.OnClickListener()){
            public void onClick(View v){
                setResult(RESULT_OK);
            }
        }
    }



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
