package com.example.legodmsproject;

import com.example.legodmsproject.JDBC.LegoDatabaseConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Elia Schwartz
 * October 23, 2025,
 * Software Development 1 - CEN 3024C 14877
 * LegoDMSController.java
 * Purpose: This java class controls how the
 * user navigate between website pages.
 */
@Controller
public class LegoDMSController {
    List<LegoSet> legoSets = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs a new LegoDMSController to allow database usage
     * and query execution in the program with JDBC Template
     * @param jdbcTemplate - Assist in properly executing query statements
     */
    public LegoDMSController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * The first page that the user see after entering the website url
     * @param model - Assist in the displaying data to the viewer
     * @return String - Display the Welcome.HTML page
     */
    @GetMapping("/Welcome")
    public String welcomePage(Model model){
        model.addAttribute("Welcome", new LegoSet());
            return "Welcome";
    }
    /**
     * Allow the user to switch to a different page
     * @param legoSet - Represent a LEGO object
     * @param model - Assist in the displaying data to the viewer
     * @return String - Let the program know what webpage to display next (LEGOMenu)
     */
    @PostMapping("/Welcome")
    public String welcomeSubmit(LegoSet legoSet, Model model){
        model.addAttribute("Welcome", legoSet);
        return "LEGOMenu";
    }

    /**
     * The transition from the welcome page to the lego menu
     * @param model - Assist in the displaying data to the viewer
     * @return String - Display the LEGOMenu.HTML webpage
     */
    @GetMapping("/LEGOMenu")
    public String legoPage(Model model){
        model.addAttribute("LEGOMenu", new LegoSet());
        return "LEGOMenu";
    }
    /**
     * The action that happens after getting onto the Lego Menu page
     * @param legoSet - Passes in an object of a LEGO
     * @param model -Assist in the displaying data to the viewer
     * @return String - The name of one of the options in the LEGOMenu
     */
    @PostMapping("/LEGOMenu")
    public String legoSubmit (LegoSet legoSet, Model model){
        model.addAttribute("LEGOMenu", legoSet);
        return "AddSetFromDatabase";
    }


    /**
     * To navigate from the LEGO Menu to the add set from database page
     * @param model - Assist in the displaying data to the viewer
     * @return String - Display the AddSetFromDatabase.HTML webpage
     */
    @GetMapping("/AddSetFromDatabase")
    public String addSetFromDatabasePage(Model model){
        model.addAttribute("AddSetFromDatabase", new LegoSet());
        return "AddSetFromDatabase";
    }
    /**
     * The action of adding sets from a database and what happens after
     * @param legoSet - LEGO Set Object
     * @param model - Assist in the displaying data to the viewer
     * @return String - Navigate back to the LEGOMenu
     */
    @PostMapping("/AddSetFromDatabase")
    public String addSetFromDatabaseSubmit (@RequestParam("path") String path, LegoSet legoSet, Model model) throws SQLException {
        LegoDatabaseConnection.getConnection(path);
        model.addAttribute("AddSetFromDatabase", legoSet);
        return "LEGOMenu";
    }

    /**
     * To navigate from the LEGO Menu to the add set manually page
     * @param model - Assist in the displaying data to the viewer
     * @return String - Displays the addSetManuallyPage to gather
     * user's lego set details
     */
    @GetMapping("/AddSetManually")
    public String addSetManuallyPage(Model model){
        model.addAttribute("AddSetManually", new LegoSet());
        return "AddSetManually";
    }
    /**
     * Allow the user to add a lego set information to the collection
     * and validating the user input
     * @param legoSet - Represent a LEGO object
     * @param model - Assist in the displaying data to the viewer
     * @return String - Navigate to the LEGoMenu once finished with the
     * current webpage
     */
    @PostMapping("/AddSetManually")
    public String addSetManuallySubmit (
            @Valid @ModelAttribute("AddSetManually") LegoSet legoSet,
            BindingResult bindingResult,
            Model model) {

        model.addAttribute("AddSetManually", legoSet);
        // Respond to errors
        if (bindingResult.hasErrors()) {
            return "AddSetManually";
        }
        String query = "INSERT INTO LegoSet VALUES(?, ?, ?, ?, ?, ?)";
        int add = jdbcTemplate.update(query, "SetNumber, Name, Theme, Pieces, ReleaseDate, Price");
        model.addAttribute("insert", add);
        return "LEGOMenu";
    }

    /**
     * Searching for the removeSetPage to obtain the information to start
     * removing a lego set
     * @param model - Assist in the displaying data to the viewer
     * @return String - Displays the RemoveSet.HTML webpage where the
     * user can complete te action
     */
    @GetMapping("/RemoveSet")
    public String removeSetPage(Model model){
        model.addAttribute("RemoveSet", new LegoSet());
        return "RemoveSet";
    }
    /**
     * The action of removing a lego set after getting onto the page
     * @param legoSet - Represents a Lego Set Object
     * @param model - Assist in the displaying data to the viewer
     * @return String - Navigating to the LEGOMenu.HTML webpage after the
     * removal of a LEGO set
     */
    @PostMapping("/RemoveSet")
    public String removeSetSubmit (@ModelAttribute("RemoveSet") LegoSet legoSet,
                                   Model model) {
        String legoName = legoSet.getName();

        boolean legoFound = false;

        Iterator<LegoSet> iterator = legoSets.iterator();
        while(iterator.hasNext()){
            LegoSet lego = iterator.next();
            if(lego.getName().equalsIgnoreCase(legoName)){
                iterator.remove();
                legoFound = true;
                break;
            }
        }

        if(!legoFound){
            model.addAttribute("errorMessage", "System can not remove the LEGO set");
            return "RemoveSet";
        }

        model.addAttribute("message", "You are removing " +
                legoName + " from the list " +
                        "of LEGO sets" );

        String query = "DELETE FROM LegoSet WHERE Name = ?";
        int delete = jdbcTemplate.update(query);
        model.addAttribute("remove", delete);
        model.addAttribute("message", "You are deleting a lego set from your collection");
        return "LEGOMenu";
    }

    /**
     * Displays the webpage to visually show what LEGO the user has
     * @param model - Assist in the displaying data to the viewer
     * @return String - Navigate to the display page
     */
    @GetMapping("/DisplaySet")
    public String displaySetPage(Model model){
        String query = "SELECT * FROM LegoSet";
        List show = jdbcTemplate.queryForList(query);
        model.addAttribute("present", show);
        model.addAttribute("DisplaySet", new LegoSet());
        return "DisplaySet";
    }
    /**
     * Allow the user to see the entire list of LEGO in their collection
     * @param legoSet - Represents a Lego Set Object
     * @param model - Assist in the displaying data to the viewer
     * @return String - The transition to the LEGOMenu.HTML webpage
     * from the current page
     */
    @PostMapping("/DisplaySet")
    public String displaySetSubmit (LegoSet legoSet, Model model) {
        for(LegoSet lego : legoSets){
            model.addAttribute("lego", lego);
        }
        model.addAttribute("DisplaySet", legoSet);
        return "LEGOMenu";
    }

    /**
     * Allow the user to get onto the page where they can alter a
     * lego attribute
     * @param model - Assist in the displaying data to the viewer
     * @return String - Display the UpdateSet.HTML webpage to the user
     */
    @GetMapping("/UpdateSet")
    public String updateSetPage(Model model){
        model.addAttribute("UpdateSet", new LegoSet());
        return "UpdateSet";
    }
    /**
     * Demonstrate what happens after an action to update a set attribute
     * @param legoSet - {@code LegoSet} Object
     * @param model - Assist in the displaying data to the viewer
     * @return String - Navigate to the LEGOMenu.HTML page from the
     * UpdateSet.HTML webpage after the user changes an attribute
     */
    @PostMapping("/UpdateSet")
    public String updateSetSubmit (LegoSet legoSet, Model model) {
        model.addAttribute("UpdateSet", legoSet);
        return "LEGOMenu";
    }

    /**
     * Allow the user to navigate to the retrieveTotalCost page from the menu
     * @param model - Assist in the displaying data to the viewer
     * @return String - Display the RetrieveTotalCost.HTML webpage to the user
     */
    @GetMapping("/RetrieveTotalCost")
    public String retrieveTotalCostPage(LegoSet legoSet, Model model){
        String query = "SELECT SUM(Price) AS TotalCost FROM LegoSet";
        Double totalCost = jdbcTemplate.queryForObject(query, Double.class);
        model.addAttribute("getTotal", totalCost);
        model.addAttribute("RetrieveTotalCost", new LegoSet());
        return "RetrieveTotalCost";
    }
    /**
     * The user can see the output of the total cost they spent on their collection
     * @param legoSet - {@code LegoSet} object
     * @param model - Assist in the displaying data to the viewer
     * @return String - Navigate to the LEGOMenu.HTML webpage
     * after getting the total
     */
    @PostMapping("/RetrieveTotalCost")
    public String retrieveTotalCostSubmit (LegoSet legoSet, Model model) {
        model.addAttribute("RetrieveTotalCost", legoSet);
        return "LEGOMenu";
    }

    /**
     * Allow the user to navigate to the exit page from the menu
     * @param model - Assist in the displaying data to the viewer
     * @return String - Display the Exit.HTML webpage, what the users interact with
     */
    @GetMapping("/Exit")
    public String ExitPage(Model model){
        model.addAttribute("Exit", new LegoSet());
        return "Exit";
    }

    /**
     * Redirect the user based on the button press "Exit" or "Return to LEGOMenu"
     * @param action - The value of the users choice
     * @return Sting - Navigate to either the LEGOMenu.HTML or GoodBye.HTML webpage
     *
     */
    @PostMapping("/Exit")
    public String exitSubmit (@RequestParam(value = "button1", required = false) String action) {
        if("button1".equals(action)){
            return "GoodBye";
        }
            return "LEGOMenu";
    }

    /**
     * Allow the user to see the signing off page
     * @param model - Assist in the displaying data to the viewer
     * @return String - Shows the GoodBye.HTML to the user
     */
    @GetMapping("/GoodBye")
    public String goodbyePage(Model model){
        model.addAttribute("GoodBye", new LegoSet());
        return "GoodBye";
    }
    /**
     * The user will stay on the goodbye Page and safety close the web browser window
     * @return String - GoodBye webpage
     */
    @PostMapping("/GoodBye")
    public String goodbyeSubmit(){
        return "GoodBye";
    }
}
