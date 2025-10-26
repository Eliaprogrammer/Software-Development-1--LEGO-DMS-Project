package com.example.legodmsproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/Welcome")
    public String welcomePage(Model model){
        model.addAttribute("Welcome", new LegoSet());
            return "Welcome";
    }
    @PostMapping("/Welcome")
    public String welcomeSubmit(LegoSet legoSet, Model model){
        model.addAttribute("Welcome", legoSet);
        return "LEGOMenu";
    }


    @GetMapping("/LEGOMenu")
    public String legoPage(Model model){
        model.addAttribute("LEGOMenu", new LegoSet());
        return "LEGOMenu";
    }
    @PostMapping("/LEGOMenu")
    public String legoSubmit (LegoSet legoSet, Model model){
        model.addAttribute("LEGOMenu", legoSet);
        return "AddSetFromFile";
//        return "AddSetManually";
//        return "RemoveSet";
//        return "DisplaySet";
//        return "UpdateSet";
//        return "RetrieveTotalCost";
//        return "Exit";

    }


    @GetMapping("/AddSetFromFile")
    public String addSetFromFilePage(Model model){
        model.addAttribute("AddSetFromFile", new LegoSet());
        return "AddSetFromFile";
    }
    @GetMapping("/AddSetManually")
    public String addSetManuallyPage(Model model){
        model.addAttribute("AddSetManually", new LegoSet());
        return "AddSetManually";
    }
    @GetMapping("/RemoveSet")
    public String removeSetPage(Model model){
        model.addAttribute("RemoveSet", new LegoSet());
        return "RemoveSet";
    }
    @GetMapping("/DisplaySet")
    public String displaySetPage(Model model){
        model.addAttribute("DisplaySet", new LegoSet());
        return "DisplaySet";
    }
    @GetMapping("/UpdateSet")
    public String updateSetPage(Model model){
        model.addAttribute("UpdateSet", new LegoSet());
        return "UpdateSet";
    }
    @GetMapping("/RetrieveTotalCost")
    public String retrieveTotalCostPage(Model model){
        model.addAttribute("RetrieveTotalCost", new LegoSet());
        return "RetrieveTotalCost";
    }
    @GetMapping("/Exit")
    public String ExitPage(Model model){
        model.addAttribute("Exit", new LegoSet());
        return "Exit";
    }

}
