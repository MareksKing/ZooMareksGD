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

    // @GetMapping("/zooListByAnimal")
    // public String selectAllZoosByAnimal(Animal animal){
    //     model.addAttribute("allAnimals", otherService.allAnimalsInAllZoos());
    //     return "search-animal-page";
    // }

    // @PostMapping("/zooListByAnimal")
    // public String postSelectAllZoosByAnimal(Animal animal, BindingResult result, Model model){
    //     if (result.hasErrors()) {
    //         return "search-animal-page";
    //     }
    //     model.addAttribute("zoos", otherService.selectAllZoosByAnimal(animal));
    //     return "all-animal-page";  
    // }

    @GetMapping("/zooListByAddress")
    public String searchForZooByAddress(Town town){
        return "all-address-page";
    }

    @PostMapping("/zooListByAddress")
    public String postSearchForZooByAddress(Town town, Model model){
        Zoo zoo = otherService.searchForZooByAddress(town);

        return "redirect:/zoo/" + zoo.getZooId();
      }

    @GetMapping("/home")
    public String homePage(){
        return "home-page";
    }
}
