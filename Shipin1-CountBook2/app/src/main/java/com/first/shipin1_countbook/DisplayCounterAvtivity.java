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
    int countvalue ;
    int countinitial;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_activity);

        //ToDo set the countinitial to initial value, then press reset to set the currentvalue = initialvalue
        //ToDo add "save" and "delete" button
        Bundle bundle = getIntent().getExtras();
        TextView counterName = (TextView) findViewById(R.id.CounterTextView);
        counterName.setText(bundle.getString("CounterName"));

        TextView counterComment = (TextView) findViewById(R.id.counterComment);
        counterComment.setText(bundle.getString("CounterComment"));

        showValue = (TextView) findViewById(R.id.CountNumberDisplay);
        countvalue = (bundle.getInt("CounterCurrentValue"));
        showValue.setText(Integer.toString(countvalue));
        countinitial = (bundle.getInt("CounterInitialValue"));



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
