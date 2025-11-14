package com.example.legodmsproject;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Elia Schwartz
 * October 10, 2025,
 * CEN 3024C - 14877 Software Development 1
 * LegoSet.java
 * This class describes a Lego set, showing the attributes associated with each LEGO Set
 */
public class LegoSet {
    @Positive(message = "Number must be positive")
    @Digits(fraction = 0, integer = 1, message = "Must be a whole number")
    private int setNumber;

    @NotBlank(message = "Name is required.")
    @Digits(fraction = 0, integer = 0, message = "Must contain letters")
    private String name;

    @NotBlank(message = "Theme is required")
    @Digits(fraction = 1, integer = 1, message = "Must contain letters")
    private String theme;

    @Positive(message = "Pieces must be greater than 0")
    private int pieces;

    @DateTimeFormat(pattern = "MM/DD/YYY")
    @NotNull(message = "The input was not a valid date")
    ReleaseDate releaseDate;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.01", message = "Price must be positive.")
    private double price;

    /**
     * Create an empty {@code LegoSet} object instance
     * without setting any of the values
     */
    public LegoSet(){
        int setNumber;
        String name;
        String theme;
        int pieces;
        ReleaseDate releaseDate;
        double price;
    }

    /**
     * Create a {@code LegoSet} object and initializing each value
     * @param setNumber - Numerically list the Lego Set
     * @param name - A name that identify the Lego Set
     * @param theme - Identify the type of Lego Set it belongs to
     * @param pieces - The number of pieces the Lego Set contains
     * @param releaseDate - Represent the date the Lego Set was release to the public
     * @param price - How much it cost to get that particular Lego Set
     */
    public LegoSet(int setNumber, String name, String theme, int pieces, ReleaseDate releaseDate, double price){
        this.setNumber = setNumber;
        this.name = name;
        this.theme = theme;
        this.pieces = pieces;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    /**
     * Allow the user to get the current set number
     * value in the variable to use/display it
     * @return int - The most recent integer value in a numerically {@code LegoSet} list
     */
    public int getSetNumber(){
        return this.setNumber;
    }

    /**
     * Allow the user to initialize or change the variable value
     * @param setNumber - Any integer number the user wants it to be
     */
    public void setSetNumber(int setNumber){
        this.setNumber = setNumber;
    }

    /**
     * Allow the user to get the current name of a {@code LegoSet} object
     * value in the variable to use/display it
     * @return String - A word or phrase that describes what the Lego Set represents
     */
    public String getName(){
        return this.name;
    }

    /**
     * Initialize or change the variable name value for a {@code LegoSet} object
     * @param name - A word or phrase that describes what the Lego Set represents
     *             that the user input
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Allow the user to get the current theme for
     * A {@code LegoSet} object to use/display it
     * @return String - A word or phrase of the category the
     * Lego Set belongs to
     */
    public String getTheme(){
        return this.theme;
    }

    /**
     * Initialize or change the theme for a particular {@code LegoSet}
     * @param theme - The name or category assign to the {@code LegoSet}
     */
    public void setTheme(String theme){
        this.theme = theme;
    }

    /**
     * Get the current Piece count value for a {@code LegoSet}
     * to use/display it
     * @return int - An integer that represent the current number of pieces
     * the {@code LegoSet} contains
     */
    public int getPieces(){
        return this.pieces;
    }

    /**
     * Initialized or change the value of Pieces for a {@code LegoSet}
     * @param pieces - An integer represent how many lego pieces it contains
     */
    public void setPieces(int pieces){
        this.pieces = pieces;
    }

    /**
     * Get the current {@code releaseDate} object value
     * @return The {@code releaseDate} object
     */
    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Assigning the ReleaseDate values
     * @param releaseDate - two-digit number for month and day,
     *                    and four-digit number for the year
     */
    public void setReleaseDate(ReleaseDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Get the current price value assigned to a {@code LegoSet} object
     * @return double - Represent currency, dollars and cents
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Assigning a monetary value to a {@code LegoSet} object
     * @param price - How much it is considered to be worth or cost
     *              at the time of purchase?
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Method: toString
     * Return: String - Display content in a certain way
     */
    @Override
    public String toString(){
        return getSetNumber() + "|" + getName() + "|" + getTheme() + "|" + getPieces() + "|" + getReleaseDate() + "|" + getPrice();
    }
}
