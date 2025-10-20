package com.example.legodmsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class LegoCollectionTest {

    List<LegoSet> legoSets;
    LegoCollection legoCollection;


    @BeforeEach
    void setUp(){
        legoSets = new ArrayList<>();
        legoCollection = new LegoCollection(legoSets);

    }


    @Test
    void testAddSetFromFileSuccessfully() {
        InputStream originalInput = System.in;
        try{
            String expected = "C:\\Users\\Owner\\OneDrive\\Desktop\\LEGO Collection List.txt";

            ByteArrayInputStream changeInputFormat = new ByteArrayInputStream(expected.
                    getBytes());
            System.setIn(changeInputFormat);

            assertTrue(legoCollection.addSetFromFile(), "Something happen that " +
                    "prevented the file from displaying.");
        } finally{
            System.setIn(originalInput);
        }
    }

    @Test
    void testAddSetFromFileFail(){
        InputStream originalInput = System.in;
        try{
            String wrongLocation = "I love pandas";
            ByteArrayInputStream changeInputFormat = new ByteArrayInputStream(
                    wrongLocation.getBytes());
            System.setIn(changeInputFormat);

            assertFalse(legoCollection.addSetFromFile(), "The file should not match");
        } finally{
            System.setIn(originalInput);
        }
    }

    @Test
    void testAddSetManually() {
        InputStream originalInput = System.in;
        try{
            String expected = String.join("\n",
                    "3",
                    "1", "Tuxedo Cat", "Ideas", "1710", "6/1/2024", "99.99",
                    "2", "101 Dalmatians Puppy", "Disney", "1722", "6/1/2025", "149.99",
                    "3", "Auspicious Dragon", "Luna New Year", "1171", "1/1/2024", "89.99"
                    ) + "\n";

            ByteArrayInputStream changeInputFormat = new ByteArrayInputStream(
                    expected.getBytes());
            System.setIn(changeInputFormat);

            assertTrue(legoCollection.addSetManually(), "The set did not get " +
                    "added to the list correctly");
            assertEquals(3, legoSets.size());
        } finally{
            System.setIn(originalInput);
        }
    }

    @Test
    void testRemoveSet() {
        InputStream originalInput = System.in;
        try{

            ReleaseDate towerReleaseDate = new ReleaseDate(6,1,2013);
            LegoSet tower = new LegoSet(4, "Leaning Tower of Pisa",
                    "Architecture", 345, towerReleaseDate, 34.99);
            legoSets.add(tower);

            System.out.println("Testing size "  + legoSets.size());
            String name = "Leaning Tower of Pisa";
            ByteArrayInputStream changeInputFormat = new ByteArrayInputStream(
                    name.getBytes());
            System.setIn(changeInputFormat);

            LegoSet remove = legoCollection.removeSet();

            System.out.println("Testing size "  + legoSets.size());
            assertNotNull(remove, "There was a problem with removing a set");
            assertEquals(tower, remove, "Both instance should match but they didn't");
            assertFalse(legoSets.contains(tower), "The element is still in the list");

        } finally{
            System.setIn(originalInput);
        }
    }

    @Test
     void testDisplaySet() {
        PrintStream originalOut = System.out;
        try {

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            for (LegoSet legoSet : legoSets) {
                System.out.println(legoSet);
                assertEquals(legoSet, legoCollection.displaySet());
            }
        }finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testUpdateSet() {
    }

    @Test
    void testRetrieveTotal() {

        double expected = 0;

        for( LegoSet lego : legoSets){
            expected += lego.getPrice();
        }
        double actually = legoCollection.retrieveTotal();

        assertEquals(expected, actually);
    }
}