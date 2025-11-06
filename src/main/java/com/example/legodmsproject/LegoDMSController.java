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
    public LegoDMSController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Method: welcomePage
     * param model
     * return: String
     * Purpose: The first page that the user
     * see after entering the url
     */
    @GetMapping("/Welcome")
    public String welcomePage(Model model){
        model.addAttribute("Welcome", new LegoSet());
            return "Welcome";
    }

    /**
     * Method: welcomeSubmit
     * param legoSet
     * param model
     * return: String
     * Purpose: Allow the user to switch
     * to a different page
     */
    @PostMapping("/Welcome")
    public String welcomeSubmit(LegoSet legoSet, Model model){
        model.addAttribute("Welcome", legoSet);
        return "LEGOMenu";
    }

    /**
     * Method: legoPage
     * param model
     * return: String
     * Purpose: the transition from the welcome page to
     * the lego menu
     */
    @GetMapping("/LEGOMenu")
    public String legoPage(Model model){
        model.addAttribute("LEGOMenu", new LegoSet());
        return "LEGOMenu";
    }

    /**
     * Method: legoSubmit
     * param legoSet
     * param model
     * return: String
     * Purpose: The action that happens after
     * getting onto the Lego Menu page
     */
    @PostMapping("/LEGOMenu")
    public String legoSubmit (LegoSet legoSet, Model model){
        model.addAttribute("LEGOMenu", legoSet);
        return "AddSetFromFile";
    }


    /**
     * Method: addSetFromFilePage
     * param model
     * return: String
     * Purpose:To navigate from the LEGO Menu to the add set
     * from file page
     */
    @GetMapping("/AddSetFromFile")
    public String addSetFromFilePage(Model model){
        model.addAttribute("AddSetFromFile", new LegoSet());
        return "AddSetFromFile";
    }

    /**
     * Method: addSetFromFileSubmit
     * param legoSet
     * param model
     * return: String
     * Purpose: the action of adding sets from a file
     * and what happens after
     */
    @PostMapping("/AddSetFromFile")
    public String addSetFromFileSubmit (@RequestParam("path") String path, LegoSet legoSet, Model model) throws SQLException {
        LegoDatabaseConnection.getConnection(path);
        model.addAttribute("AddSetFromFile", legoSet);
        return "LEGOMenu";
    }

    /**
     * Method: addSetManuallyPage
     * param model
     * return: String
     * Purpose:To navigate from the LEGO Menu to the add set
     * manually page
     */
    @GetMapping("/AddSetManually")
    public String addSetManuallyPage(Model model){
        model.addAttribute("AddSetManually", new LegoSet());
        return "AddSetManually";
    }
    /**
     * Method: addSetManuallySubmit
     * param legoSet
     * param model
     * return: String
     * Purpose: Allow the user to add a lego set information
     * to the collection and validating the user input
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
        String query = "INSERT INTO LegoSet" +
                "(SetNumber, Name, Theme, Pieces, ReleaseDate, Price)" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        int add = (int)jdbcTemplate.update(query, "SetNumber, Name, Theme, Pieces, ReleaseDate, Price");
        model.addAttribute("insert", add);
        return "LEGOMenu";
    }

    /**
     * Method: removeSetPage
     * param model
     * return: String
     * Purpose: Searching for the removeSetPage
     * to obtain the information to start
     * removing a lego set
     */

    @GetMapping("/RemoveSet")
    public String removeSetPage(Model model){
        model.addAttribute("RemoveSet", new LegoSet());
        return "RemoveSet";
    }
    /**
     * Method: removeSetSubmit
     * param legoSet
     * param model
     * return: String
     * Purpose: The action of removing a
     * lego set after getting onto the page
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
     * Method: displaySetPage
     * param model
     * return: String
     * Purpose: Navigate to the display page
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
     * Method: displaySetSubmit
     * param legoSet
     * param model
     * return: String
     * Purpose: Allow the user to see
     * the entire list of LEGO in their collection
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
     * Method: updatedSetPage
     * param model
     * return: String
     * Purpose: Allow the user to get onto
     * the page where they can alter a
     * lego attribute
     */
    @GetMapping("/UpdateSet")
    public String updateSetPage(Model model){
        model.addAttribute("UpdateSet", new LegoSet());
        return "UpdateSet";
    }
    /**
     * Method: updateSetSubmit
     * param legoSet
     * param model
     * return: String
     * Purpose: Allow the user the action
     * to update a set attribute
     */
    @PostMapping("/UpdateSet")
    public String updateSetSubmit (LegoSet legoSet, Model model) {
        model.addAttribute("UpdateSet", legoSet);
        return "LEGOMenu";
    }

    /**
     * Method: retrieveTotalCostPage
     * param model
     * return: String
     * Allow the user to navigate to
     * the retrieveTotalCost page from the menu
     */
    @GetMapping("/RetrieveTotalCost")
    public String retrieveTotalCostPage(LegoSet legoSet, Model model){
        String query = "SELECT SUM(Price) AS TotalCost FROM LegoSet";
        Double totalCost = (Double) jdbcTemplate.queryForObject(query, Double.class);
        model.addAttribute("getTotal", totalCost);
        model.addAttribute("RetrieveTotalCost", new LegoSet());
        return "RetrieveTotalCost";
    }

    /**
     * Method: retrieveTotalCostSubmit
     * param legoSet
     * param model
     * return: String
     * Purpose: The user can see the output of
     * the total cost they spent on their collection
     */
    @PostMapping("/RetrieveTotalCost")
    public String retrieveTotalCostSubmit (LegoSet legoSet, Model model) {
        model.addAttribute("RetrieveTotalCost", legoSet);
        return "LEGOMenu";
    }

    /**
     * Method: ExitPage
     * param model
     * return: String
     * Purpose: Allow the user to navigate to
     * the exit page from the menu
     */
    @GetMapping("/Exit")
    public String ExitPage(Model model){
        model.addAttribute("Exit", new LegoSet());
        return "Exit";
    }

    /**
     * Method: exitSubmit
     * param action
     * return: Sting
     * Purpose: Redirect the user
     * based on the button press
     */
    @PostMapping("/Exit")
    public String exitSubmit (@RequestParam(value = "action", required = false) String action) {
        if("button1".equals(action)){
            return "GoodBye";
        }
            return "LEGOMenu";
    }

    /**
     * Method: goodbyePage
     * param model
     * return: String
     * Purpose: Allow the user to see the signing off
     * page
     */
    @GetMapping("/GoodBye")
    public String goodbyePage(Model model){
        model.addAttribute("GoodBye", new LegoSet());
        return "GoodBye";
    }

    /**
     * Method: goodbyeSubmit
     * return: String
     * Purpose: Manage what happens after the
     * use gets to the goodbye Page
     */
    @PostMapping("/GoodBye")
    public String goodbyeSubmit(){
        return "GoodBye";
    }
}
