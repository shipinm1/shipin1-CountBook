package com.first.shipin1_countbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by gsp on 2017/9/24.
 */

public class DisplayCounterAvtivity extends AppCompatActivity{
    TextView showValue;
    int countvalue = 0;
    int countinitial = 12;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_activity);


        Bundle bundle = getIntent().getExtras();
        TextView txtview = (TextView) findViewById(R.id.CounterTextView);
        txtview.setText(bundle.getString("CounterName"));

        showValue = (TextView) findViewById(R.id.CountNumberDisplay);


    }
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
}
