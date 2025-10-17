package com.example.legodmsproject;

import java.util.Scanner;

/**
 * Elia Schwartz
 * October 10, 2025,
 * CEN 3024C 14877 Software Development
 * Menu.java
 * This file is an interface that only holds the method declarations
 */
public interface Menu {
    String displayLegoMenu();
    boolean addSetFromFile(Scanner input);
    boolean addSetManually();
    LegoSet removeSet();
    LegoSet displaySet();
    LegoSet updateSet();
    double retrieveTotal();
    boolean exit();
}
