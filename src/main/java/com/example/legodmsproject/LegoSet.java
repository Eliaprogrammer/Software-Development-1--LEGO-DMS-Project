package com.example.legodmsproject;
/**
 * Elia Schwartz
 * October 10, 2025,
 * CEN 3024C - 14877 Software Development 1
 * LegoSet.java
 * This class describes a Lego set, showing the attributes associated with each LEGO Set
 */
public class LegoSet {
    private int setNumber;
    private String name;
    private String theme;
    private int pieces;
    ReleaseDate releaseDate;
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
    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(ReleaseDate releaseDate) {
        this.releaseDate = releaseDate;
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
