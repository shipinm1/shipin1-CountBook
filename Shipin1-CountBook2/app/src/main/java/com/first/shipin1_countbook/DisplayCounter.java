package com.first.shipin1_countbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by gsp on 2017/9/24.
 */

public class DisplayCounter extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_activity);
        Bundle bundle = getIntent().getExtras();
        TextView txtview = (TextView) findViewById(R.id.CounterTextView);
        txtview.setText(bundle.getString("CounterName"));
        

    }

}
