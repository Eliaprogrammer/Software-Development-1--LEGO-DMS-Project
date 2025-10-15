package com.example.legodmsproject;

/**
 * Elia Schwartz
 * October 10, 2025,
 * CEN 302C - 148777 Software Development 1
 * ReleaseDate.java
 * This class helps create a release date object using the following attributes
 */
public class ReleaseDate {
    private int month;
    private int day;
    private int year;

    public ReleaseDate(int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth(){
        return this.month;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public int getDay(){
        return this.day;
    }
    public void setDay(int day){
        this.day = day;
    }
    public int getYear(){
        return this.year;
    }
    public void setYear(int year){
        this.year = year;
    }

    /**
     * Method: toString
     * Parameters: none
     * @return String
     * Purpose: Formating the date in a certain way
     */
    public String toString(){
        return getMonth() + "/" + getDay() +"/" + getYear();
    }
}
