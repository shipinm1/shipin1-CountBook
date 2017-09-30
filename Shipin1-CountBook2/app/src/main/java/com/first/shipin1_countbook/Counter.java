package com.first.shipin1_countbook;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by gsp on 2017/9/24.
 */

public class  Counter {
    public static ArrayList<Counter> Counters = new ArrayList<Counter>();
    private String name;
    private Date date;
    private int initValue;
    private String comment;
    private int currentValue;

    public Counter(String name, int initValue, String comment ){
        this.name = name;
        this.date = new Date();
        this.initValue = initValue;
        this.comment = comment;
        this.currentValue = initValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getInitValue() {
        return initValue;
    }

    public void setInitValue(int initValue) {
        this.initValue = initValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }




    @Override
    public String toString(){ return "Counter -- " + name + "\n \nLast modified on:  \n" + date.toString();
    }
}
