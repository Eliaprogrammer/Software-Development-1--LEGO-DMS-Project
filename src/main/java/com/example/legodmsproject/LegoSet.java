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

    public LegoSet(){
        int setNumber;
        String name;
        String theme;
        int pieces;
        ReleaseDate releaseDate;
        double price;
    }

    public LegoSet(int setNumber, String name, String theme, int pieces, ReleaseDate releaseDate, double price){
        this.setNumber = setNumber;
        this.name = name;
        this.theme = theme;
        this.pieces = pieces;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public int getSetNumber(){
        return this.setNumber;
    }
    public void setSetNumber(int setNumber){
        this.setNumber = setNumber;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getTheme(){
        return this.theme;
    }
    public void setTheme(String theme){
        this.theme = theme;
    }
    public int getPieces(){
        return this.pieces;
    }
    public void setPieces(int pieces){
        this.pieces = pieces;
    }
    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(ReleaseDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Method: toString
     * Purpose: Display content in a certain way
     * @Parameters:none
     * Return: String
     *
     */
    @Override
    public String toString(){
        return getSetNumber() + "|" + getName() + "|" + getTheme() + "|" + getPieces() + "|" + getReleaseDate() + "|" + getPrice();
    }
}
