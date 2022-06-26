package com.zoo.mareks.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.Town;
import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.service.IOtherService;

@Controller
public class OtherController {

    @Autowired
    private IOtherService otherService;

    // @GetMapping("/zooListByAnimal") //localhost:8080/zooListByAnimal
    // public String selectAllZoosByAnimal(Animal animal, Model model){
    // model.addAttribute("animal", otherService.allAnimalsInAllZoos());
    // return "search-animal-page";
    // }

    // @PostMapping("/zooListByAnimal")
    // public String postSelectAllZoosByAnimal(Animal animal, Model model){
    // model.addAttribute("zoos", otherService.selectAllZoosByAnimal(animal));
    // return "all-animal-page";
    // }

    /**
     * This function will return a list of all zoos in a given town
     * 
     * @param town the town object that is passed to the method
     * @return A string.
     */
    @GetMapping("/zooListByAddress")
    public String searchForZooByAddress(Town town) {
        return "all-address-page";
    }

    /**
     * It takes a town object, searches for a zoo in that town, and then redirects
     * to the zoo's page
     * 
     * @param town  Town
     * @param model Model
     * @return A redirect to the zoo page.
     */
    @PostMapping("/zooListByAddress") //localhost:8080/zooListByAddress
    public String postSearchForZooByAddress(Town town, Model model) {
        Zoo zoo = otherService.searchForZooByAddress(town);

        return "redirect:/zoo/" + zoo.getZooId();
    }

    /**
     * It returns the site for the user
     * 
     * @return The home-page.html file
     */
    @GetMapping("/home") //localhost:8080/home
    public String homePage() {
        return "home-page";
    }
}
