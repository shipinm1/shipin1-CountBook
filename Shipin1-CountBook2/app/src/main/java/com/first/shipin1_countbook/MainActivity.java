package com.first.shipin1_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE="com.first.shipin1_countbook.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createCounter(View view){
        Intent intent = new Intent(this, NewCounter.class);
        //intent.putExtra(EXTRA_MESSAGE, );
        startActivity(intent);
    }
}
