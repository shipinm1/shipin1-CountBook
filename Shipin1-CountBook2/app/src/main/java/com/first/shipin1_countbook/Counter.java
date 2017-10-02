package com.first.shipin1_countbook;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shipin1 on 2017/9/24.
 *
 * Version 1.0
 *
 * Counter Class Description:
 * Making the Arraylist object "Counters".
 * Used to store counter related information such as name, comment, values and dates.
 * The class was used by "MainActivity" class.
 */

public class  Counter {
    public static ArrayList<Counter> Counters = new ArrayList<Counter>();
    private String name;                    //name of counter
    private Date date;                      //date when modification applied
    private int initValue;                  //the initial value of the counter
    private String comment;                 //user input comments. It is ok to be empty
    private int currentValue;               //counter current value

    /*Construction and getter and setter*/
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



    //Used to display information on the main activity page.
    @Override
    public String toString(){
        return "Counter --- " + name + "\nLast modified on:  \n" + date.toString() + "\nCurrent Value: " + currentValue;
    }
}
