package com.example.legodmsproject;

/**
 * Elia Schwartz
 * October 10, 2025,
 * CEN 302C - 148777 Software Development 1
 * ReleaseDate.java
 * This class helps create a release date object using the following attributes:
 * month, day, year
 */
public class ReleaseDate {
    private int month;
    private int day;
    private int year;

    /**
     * Construct a new {@code ReleaseDate} Object to use as another
     * attribute for a LEGO set
     * @param month - Represents which month the LEGO Set was released
     * @param day - Represents what day in the month the LEGO Set was released
     * @param year - Represents what year the LEGO Set was released
     */
    public ReleaseDate(int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Allow the user to get the current month value in
     * the variable to use/display it
     * @return int - A number between 1 and 12 to represent one
     * of the 12 months of the year
     */
    public int getMonth(){
        return this.month;
    }

    /**
     * Allow the user to set the month variable
     * to a specific integer value they enter
     * @param month - The number corresponding to a certain month
     *              that the user inputs
     */
    public void setMonth(int month){
        this.month = month;
    }

    /**
     * Allow the user to get the current day value in the variable
     * to use/display it
     * @return int - Represents one of the 31 days in a month
     */
    public int getDay(){
        return this.day;
    }

    /**
     * Allow the user to set the day variable
     * to a specific integer value they enter
     * @param day - The day number value that
     *            the user wants to set day to
     */
    public void setDay(int day){
        this.day = day;
    }

    /**
     * Allow the user to get the current year value in the
     * variable to use/display it
     * @return int - Represented a four digit year
     */
    public int getYear(){
        return this.year;
    }

    /**
     * Allow the user to set the year variable
     * to a specific integer value they enter
     * @param year - A four digit integer the user
     *             decides the year should be
     */
    public void setYear(int year){
        this.year = year;
    }

    /**
     * Method: toString
     * @return String - Formating the date in a certain way
     */
    public String toString(){
        return getMonth() + "/" + getDay() +"/" + getYear();
    }
}
