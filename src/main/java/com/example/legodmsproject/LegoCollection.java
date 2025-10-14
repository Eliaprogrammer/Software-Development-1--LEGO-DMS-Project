package com.example.legodmsproject;

/**
 * Elia Schwartz
 * October 10, 2025,
 * CEN 3024C 14877 Software Development 1
 * LegoCollection.java
 * This class has the implementation code for each method declaration in the interface
 * that the user will interact with when the program is run,
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LegoCollection implements Menu{
    LegoSet legoSet = new LegoSet();

    List<LegoSet> legoSets = new ArrayList<>();

    boolean isValid = false;

    /**
     * Method: displayLegoMenu
     * @return: String
     * Parameter: none
     * Purpose: The user will be capable of interacting
     * with the menu options
     */
    @Override
    public String displayLegoMenu(){
        Scanner menu = new Scanner(System.in);
        String menuOption;
        do {
            System.out.println("Choose from the following menu options: (Please Enter " +
                    "the corresponding menu option number)");
            System.out.println("1. Add LEGO Set(s) From a file");
            System.out.println("2. Add LEGO Set(s) Manually");
            System.out.println("3. Remove LEGO Set");
            System.out.println("4. Display All LEGO Sets");
            System.out.println("5. Update LEGO Set");
            System.out.println("6. Retrieve Total Cost of LEGO Set");
            System.out.println("7. Exit");

            menuOption = menu.nextLine();

            switch (menuOption) {
                case "1":
                    addSetFromFile();
                    isValid = true;
                    break;
                case"2":
                    addSetManually();
                    isValid = true;
                    break;
                case "3":
                    removeSet();
                    isValid = true;
                    break;
                case "4":
                    displaySet();
                    isValid = true;
                    break;
                case "5":
                    updateSet();
                    isValid = true;
                    break;
                case "6":
                    retrieveTotal();
                    isValid = true;
                    break;
                case "7":
                    exit();
                    isValid = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }

        }while(!isValid);
        return menuOption;
    }

    /**
     * Method: addSetFromFile
     * Parameters: none
     * @return: boolean
     * Purpose: When called upon the user
     * can enter the location of a file to add to
     * the list
     */
    public boolean addSetFromFile() {
        Scanner input = new Scanner(System.in);
        System.out.println("You are in the process of adding LEGO set(s) from a " +
                "file to the system.");
        do {
            try {
                System.out.println("Please enter your file location/path:");
                String fileLocation = input.nextLine();
                File file = new File(fileLocation);
                Scanner fileReader = new Scanner(file);
                System.out.println();
                System.out.println("The File was successfully uploaded. You can now " +
                        "see the file details.");
                while (fileReader.hasNextLine()) {
                    String setString = fileReader.nextLine().trim();
                    if (setString.isEmpty()) continue;
                    String[] legoSetArrayParts = setString.split("\\|");
                    int setNumber = Integer.parseInt(legoSetArrayParts[0]);
                    String name = legoSetArrayParts[1];
                    String theme = legoSetArrayParts[2];
                    int pieces = Integer.parseInt(legoSetArrayParts[3]);

                    String dateString = legoSetArrayParts[4];
                    String[] dateParts = dateString.split("/");
                    int month = Integer.parseInt(dateParts[0]);
                    int day = Integer.parseInt(dateParts[1]);
                    int year = Integer.parseInt(dateParts[2]);

                    ReleaseDate releaseDate = new ReleaseDate(month, day, year);

                    double price = Double.parseDouble(legoSetArrayParts[5]);

                    LegoSet legoSet = new LegoSet(setNumber, name, theme, pieces,
                            releaseDate, price);

                    legoSets.add(legoSet);
                    System.out.println(legoSet);

                }
                isValid = true;
                System.out.println();

                while (!(file.exists())) {
                    throw new IOException();
                }
                displayLegoMenu();
                return true;

            } catch (IOException e) {
                System.out.println("System was unable to location the file you " +
                        "are trying to upload.");
                addSetFromFile();
            } catch (Exception e) {
                System.out.println("An error has occurred.");
            }
            break;
        }while(!isValid);

        return false;
    }

    /**
     * Method: addSetManually
     * @param: none
     * @return: boolean
     * Purpose: When called upon the user
     * can manually enter information details
     * about a lego set to add it to the list.
     */
    public boolean addSetManually() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("You are in the process of manually entering data " +
                    "for adding a LEGO set(s) to the system.");

            boolean okToContinue = false;
            int numberOfSets = 0;
            while (!okToContinue){
                System.out.println("How many LEGO sets you would like to add at a time?");
                String userEntry = input.nextLine();
                try {
                    numberOfSets = Integer.parseInt(userEntry);
                    if (numberOfSets < 0) throw new Exception();
                    okToContinue = true;
                } catch (Exception e) {
                    System.out.println("Please enter a number greater than 0....");
                }
            }
            for (int setIndex = 0; setIndex < numberOfSets; setIndex++) {
                while (!isValid) {
                    int setNumber = 0;
                    do {
                        try {
                            System.out.println("Please enter the nth LEGO set (set number)" +
                                    " you are adding to the system:");
                            String userSetNumber = input.nextLine();
                            setNumber = Integer.parseInt(userSetNumber);
                            if(setNumber > 0) {
                                legoSet.setSetNumber(setNumber);
                                isValid = true;
                            }

                        }catch(InputMismatchException e){
                            System.out.println("You enter the wrong input type");
                        }
                        catch (Exception e) {
                            System.out.println("The input is not a proper set number (whole number).");

                        }
                    } while (!isValid);


                    String userLegoName = "";
                    do {
                        try {
                            System.out.println("Please enter the name of the LEGO " +
                                    "set you would like to add: to the system:");
                            userLegoName = input.nextLine().trim();
                            if(userLegoName.length() < 50){
                                legoSet.setName(userLegoName);
                                isValid = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("The input must be a word. ");
                            isValid = false;

                        } catch (Exception e) {
                            System.out.println("The name must have up to 50 characters.");
                            isValid = false;

                        }
                    } while (!isValid);


                    String userLegoTheme = "";
                    do {
                        try {
                            System.out.println("Please enter the theme of the LEGO Set:");
                            if(userLegoTheme.length() < 50){
                                userLegoTheme = input.nextLine().trim();
                                legoSet.setTheme(userLegoTheme);
                                isValid = true;
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("The input must be word");
                        } catch (Exception e) {
                            System.out.println("The theme must have up to 50 characters.");
                        }
                    } while (!isValid);


                    int numberOfPieces = 0;
                    do {
                        try {
                            System.out.println("Please enter the number of Pieces the LEGO Set:");
                            String userLegoNumberOfPieces = input.nextLine().trim();
                            numberOfPieces = Integer.parseInt(userLegoNumberOfPieces);
                            legoSet.setPieces(numberOfPieces);
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("It must be a whole number.");
                        }
                    } while (!isValid);


                    do {
                        System.out.println(("Please enter the date the LEGO set was " +
                                "release: MM/DD/YYYY"));
                        try {
                            String userReleaseDate = input.nextLine().trim();
                            String[] dateParts = userReleaseDate.split("/");
                            int month = Integer.parseInt(dateParts[0]);
                            int day = Integer.parseInt(dateParts[1]);
                            int year = Integer.parseInt(dateParts[2]);
                            ReleaseDate releaseDate = new ReleaseDate(month, day, year);
                            legoSet.setReleaseDate(releaseDate);
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("The system does not recognize the date format." +
                                    "Please re-enter");
                        }
                    } while (!isValid);

                    double legoPrice = 0;
                    do {
                        try {
                            System.out.println("Please enter the price of the LEGO Set:");

                            String userLegoPrice = input.nextLine().trim();
                            legoPrice = Double.parseDouble(userLegoPrice);
                            legoSet.setPrice(legoPrice);
                            isValid = true;
                        } catch (Exception e) {
                            System.out.println("The input you have enter is not compatible.");
                        }
                    } while (!isValid);

                }
                LegoSet lego = new LegoSet(legoSet.getSetNumber(), legoSet.getName(),
                        legoSet.getTheme(),legoSet.getPieces(),
                        legoSet.getReleaseDate(), legoSet.getPrice());
                legoSets.add(lego);

                displaySet();
                displayLegoMenu();
                return true;
            }
        }
        catch(InputMismatchException e) {
            System.out.println("You have inputted the wrong value. Please try again.");
            addSetManually();
        }
        catch(Exception e){
            System.out.println("The system did not recognize something");
        }
        return false;
    }

    /**
     * Method: removeSet
     * Parameters: none
     * @return LegoSet object
     * Purpose: The user can decide what lego set to remove
     * from the list
     */
    @Override
    public LegoSet removeSet(){
        do {
            try{
            System.out.println("Please enter the LEGO set name you wish to remove and " +
                    "everything associated with it: ");
                Scanner remove = new Scanner(System.in);
                String userChoice = remove.nextLine();
                for (LegoSet lego : legoSets) {
                    if(lego.getName().equalsIgnoreCase(userChoice)){
                        System.out.println("You are removing " +
                                userChoice + "from the list " +
                                "of LEGO sets");
                        legoSets.remove(lego);
                        isValid = true;
                        break;

                    }

                }
            }catch (Exception e){
                System.out.println("System can not remove the LEGO set");
                System.out.println("The wrong data value was entered. Please try again. ");
                removeSet();
            }

        }while(!isValid);

        displayLegoMenu();
        return legoSet;

    }

    /**
     * Method: displaySet
     * Parameters: none
     * @return LegoSet object
     * Purpose: Display all the sets in the list
     */
    @Override
    public LegoSet displaySet(){
        for(LegoSet lego : legoSets){
            System.out.println(lego);
        }
        System.out.println();
        displayLegoMenu();
        return legoSet;

    }

    /**
     * Method: updateSet
     * Parameters: none
     * @return LegoSet object
     * Purpose: Update a certain lego set by
     * one of their attribute
     */
    @Override
    public LegoSet updateSet(){
        Scanner change = new Scanner(System.in);
        LegoSet foundSet = null;
        boolean shouldContinue = false;
        do {

            String userInputUpdate = "";
            do {
                System.out.println("Choose what Lego attribute would you like to update?");
                System.out.println("Set Number, Lego Name, Lego Theme, Lego Pieces, Lego " +
                        "Release Date, Lego Price");
                System.out.println("Enter exactly how you see it: ");
                userInputUpdate = change.nextLine();
                if (userInputUpdate.equals("Set Number") ||
                        userInputUpdate.equals("Lego Name") ||
                        userInputUpdate.equals("Lego Theme") ||
                        userInputUpdate.equals("Lego Pieces") ||
                        userInputUpdate.equals("Lego Release Date") ||
                        userInputUpdate.equals("Lego Price")) {
                    shouldContinue = true;
                }

            }while (!shouldContinue);
            do {
                try {
                    switch (userInputUpdate) {
                        case "Set Number":
                            do {
                                try {
                                    System.out.println("Enter the set number you would like to update:");
                                    String currentSetNumber = change.nextLine();
                                    int legoNumber = Integer.parseInt(currentSetNumber);
                                    System.out.println("What value would you like to change it to?");
                                    String updateSetNumber = change.nextLine();
                                    int newLegoNumber = Integer.parseInt(updateSetNumber);
                                    for (LegoSet legoSet : legoSets) {
                                        if (legoSet.getSetNumber() == legoNumber) {
                                            legoSet.setSetNumber(newLegoNumber);
                                            foundSet = legoSet;
                                            System.out.println("You have updated the set number" +
                                                    " from: " + currentSetNumber + " to "
                                                    + updateSetNumber);
                                            System.out.println(foundSet);
                                            shouldContinue = true;
                                            break;
                                        }
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("The input enter does not match");
                                } catch (Exception e) {
                                    System.out.println("The set number is not in the list");
                                    shouldContinue = false;
                                }
                            } while (!shouldContinue);

                            break;
                        case "Lego Name":
                            try{
                                System.out.println("Enter the Lego set name you would like" +
                                        " to update:");
                                String currentName = change.nextLine();
                                System.out.println("What name would you like to change it to?");
                                String newName = change.nextLine();

                                for (LegoSet legoSet : legoSets) {
                                    if (legoSet.getName().equalsIgnoreCase(currentName)) {
                                        legoSet.setName(newName);
                                        foundSet = legoSet;
                                        System.out.println("You have updated the Lego Name" +
                                                " from: " + currentName + " to "
                                                + newName);
                                        System.out.println(foundSet);
                                        shouldContinue = true;
                                        break;
                                    }
                                }
                            }catch(Exception e){
                                    System.out.println("The input does not exist");
                                }

                            break;
                        case "Lego Theme":
                            try {
                                System.out.println("Enter the Lego set name associated with " +
                                        "the theme you would like to update:");
                                String nameCurrentTheme = change.nextLine();
                                System.out.println("What is the Lego theme you would " +
                                        "like to update?");
                                String newTheme = change.nextLine();
                                for (LegoSet legoSet : legoSets) {
                                    if (legoSet.getName().equalsIgnoreCase(nameCurrentTheme)) {
                                        legoSet.setTheme(newTheme);
                                        foundSet = legoSet;
                                        System.out.println("You have updated the Lego theme for: " +
                                                nameCurrentTheme + " to " + newTheme);
                                        System.out.println(foundSet);
                                        shouldContinue = true;
                                        break;
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("To update theme it must be at least 50 characters");
                            }

                            break;
                        case "Lego Pieces":
                            try {
                                System.out.println("Enter the Lego name associated with the " +
                                        "pieces count you like to update:");
                                String nameCurrentPieces = change.nextLine();
                                System.out.println("What is the new pieces count would you like " +
                                        "to update?");
                                String newPieces = change.nextLine();
                                int piecesCount = Integer.parseInt(newPieces);
                                for (LegoSet legoSet : legoSets) {
                                    if (legoSet.getName().equalsIgnoreCase(nameCurrentPieces)) {
                                        legoSet.setPieces(piecesCount);
                                        foundSet = legoSet;
                                        System.out.println("You have updated the number of " +
                                                "pieces for: " + nameCurrentPieces + " " +
                                                "lego set: " + newPieces);
                                        System.out.println(foundSet);
                                        shouldContinue = true;
                                        break;
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("The input enter does not match");
                            } catch (Exception e) {
                                System.out.println("Error");
                            }
                            break;
                        case "Lego Release Date":

                            System.out.println("Enter the Lego name you want to update " +
                                    "the release date:");
                            String nameReleaseDate = change.nextLine();

                            System.out.println("Enter new release date" +
                                    " (MM/DD/YYYY) you would like to update?");
                            String newReleaseDate = change.nextLine();
                            try {
                                for (LegoSet legoSet : legoSets) {
                                    if (legoSet.getName().equalsIgnoreCase(nameReleaseDate)) {

                                        String[] dateParts = newReleaseDate.split("/");
                                        int month = Integer.parseInt(dateParts[0]);
                                        int day = Integer.parseInt(dateParts[1]);
                                        int year = Integer.parseInt(dateParts[2]);

                                        legoSet.getReleaseDate().setMonth(month);
                                        legoSet.getReleaseDate().setDay(day);
                                        legoSet.getReleaseDate().setYear(year);

                                        foundSet = legoSet;
                                        System.out.println("You have updated the release date" +
                                                " for: " + nameReleaseDate + " " +
                                                "lego set: " + legoSet.getReleaseDate());
                                        System.out.println(foundSet);
                                        shouldContinue = true;
                                        break;
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("To update release date it must be MM/DD/YYYY");
                            }
                            break;
                        case "Lego Price":
                            System.out.println("Enter the Lego name associated with the " +
                                    "price you would like to update:");
                            String legoNamePrice = change.nextLine();
                            System.out.println("What is the new price you would like to change" +
                                    " it to?");
                            try {
                                String newPrice = change.nextLine();
                                double formatPrice = Double.parseDouble(newPrice);
                                for (LegoSet legoSet : legoSets) {
                                    if (legoSet.getName().equalsIgnoreCase(legoNamePrice)) {
                                        legoSet.setPrice(formatPrice);
                                        foundSet = legoSet;
                                        System.out.println("You have updated the price for " +
                                                "the " + legoNamePrice + " lego set: " +
                                                newPrice);
                                        System.out.println(foundSet);
                                        shouldContinue = true;
                                        break;
                                    }
                                }

                            } catch (Exception e) {
                                System.out.println("You need to enter a proper price format");
                            }
                            break;
                        default:
                            System.out.println("The system does not understand" +
                                    "what you are looking for. Try entering" +
                                    "something else.");
                    }
                    if (foundSet == null) {
                        System.out.println("That Lego Set doesn't exist in the list!");
                        updateSet();
                    }
                    System.out.println();
                    displayLegoMenu();

                } catch (InputMismatchException ime) {
                    System.out.println("You have entered a mismatch type! Enter the correct " +
                            "or another type");
                    change.nextLine();
                } catch (Exception e) {
                    System.out.println("Something went wrong! Try entering attribute input " +
                            "as shown");
                }
            }while(!shouldContinue);
        }while(foundSet == null);
        return foundSet;
    }

    /**
     * Method: retrieveTotal
     * Parameters: none
     * @return double
     * Purpose: Collect the price for
     * each lego set in the list &
     * get the total cost of all sets
     */
    @Override
    public double retrieveTotal(){
        double totalCost = 0.00;
        for(LegoSet lego : legoSets){
            lego.getPrice();
            totalCost += lego.getPrice();


        }
        System.out.printf("The total cost you spent on all of your LEGO before tax is: $%.2f", totalCost);

        System.out.println();
        System.out.println();
        displayLegoMenu();
        return totalCost;
    }

    /**
     * Method: exit
     * Parameters: none
     * @return boolean
     * Purpose: Allow the user to
     * exit the program
     */
    public boolean exit(){
        System.out.println("Do you want to exit the program?");
        Scanner out = new Scanner(System.in);
        String exit = out.nextLine();
        if(exit.equalsIgnoreCase("No")){
            System.out.println("You will be redirected to the LEGO menu option");
            displayLegoMenu();
            return true;

        }
        if(exit.equalsIgnoreCase("Yes")){
            System.out.print("Press Enter");
            out.nextLine();
            System.out.println();
            System.out.println("You are now exiting the program");
            System.out.println("Program ended!");
            return true;
        }
        return false;
    }
}
