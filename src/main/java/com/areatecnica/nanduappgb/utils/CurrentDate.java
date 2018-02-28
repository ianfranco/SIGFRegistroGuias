/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ianfr
 */
public class CurrentDate {

    private Calendar calendar;
    private static SimpleDateFormat sdf;
    private Date date;
    private int month;
    private int year;
    private int day;

    public CurrentDate(Date date){
        this.date = date; 
        this.calendar = Calendar.getInstance();
        
        this.calendar.setTime(date);
        this.day = this.calendar.get(Calendar.DATE);
        this.month = this.calendar.get(Calendar.MONTH);
        this.year = this.calendar.get(Calendar.YEAR);
    }
    
    public CurrentDate(int day, int month, int year) {
        this.day = day;
        this.month = month+1;
        this.year = year;

        this.calendar = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String from = day + "/" + month + "/" + year;
        try {
            this.date = format.parse(from);
            this.calendar.setTime(date);

        } catch (ParseException p) {
        }
    }

    public CurrentDate() {
        this.calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void setDate(Date date) {
        this.calendar.setTime(date);
    }

    public Date date() {
        return calendar.getTime();
    }

    public Date getMinDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date minDate;
        String from = "01" + "/" + month() + "/" + year();
        try {
            minDate = format.parse(from);
            this.calendar.setTime(date);
            return minDate;
        } catch (ParseException p) {

        }
        return null;
    }
    
    public Date getMaxDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date maxDate;
        String from = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + "/" + month + "/" + year;
        try {
            maxDate = format.parse(from);
            this.calendar.setTime(date);
            return maxDate;
        } catch (ParseException p) {

        }
        return null;
    }

    public int day() {
        return calendar.get(Calendar.DATE);
    }

    public int month() {
        return calendar.get(Calendar.MONTH)+1;
    }

    public int year() {
        return calendar.get(Calendar.YEAR);
    }

    public String format(Date toFormat) {
        return sdf.format(toFormat);
    }
    
}
