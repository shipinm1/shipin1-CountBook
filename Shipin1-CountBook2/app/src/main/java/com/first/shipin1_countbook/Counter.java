package com.first.shipin1_countbook;

import java.util.Date;

/**
 * Created by gsp on 2017/9/24.
 */

public class Counter {
    private String name;
    private Date date;
    private Integer initValue;
    private String comment;

    public Counter(String name){
        this.name = name;
        this.date = new Date();


    }

    public void setDate(Date date){
        this.date = date;
    }
    public void setName(String name){this.name = name;}
    public void setinitValue(Integer initValue){this.initValue = initValue;}
    public void setComment(String comment){this.comment = comment;}
    public Date getDate(){
        return date;
    }
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name + "Last modified on: " + date.toString();
    }
}
