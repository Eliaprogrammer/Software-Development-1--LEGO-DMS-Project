package com.example.legodmsproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Elia Schwartz
 * October 6, 2025,
 * CEN 3024C - 14877 Software Development 1
 * LegoDmsProjectApplication.java
 * Objective: Create a database management system the user can interact with through the command line
 * and a graphical user interface that holds information about a topic we chose. I choose to do LEGO.
 * The program can add, remove, display and update LEGO in addition to retrieving the total cost of all LEGO sets
 * Some Object-oriented principles I incorporated in the system are objects, interface, encapsulation, lists (ArrayList)
 *

 */
@SpringBootApplication
public class LegoDmsProjectApplication {
    public static void main(String[] args) {
       LegoCollection LegoCollection = new LegoCollection();

       LegoCollection.displayLegoMenu();


        //SpringApplication.run(LegoDmsProjectApplication.class, args);
    }



}
