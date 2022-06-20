package com.zoo.mareks.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.service.IOtherService;

@Controller
public class OtherController {
    
    @Autowired
    private IOtherService otherService;

    @GetMapping("/zooListByAnimal")
    public String selectAllZoosByAnimal(Animal animal){
        return "all-animal-page";
    }

    @PostMapping("/zooListByAnimal")
    public String postSelectAllZoosByAnimal(@Valid Animal animal, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "all-animal-page";
        }
        model.addAttribute("allZoosWithAnimal", otherService.selectAllZoosByAnimal(animal));
        return "zoo-list-page";  
    }

    @GetMapping("/zooListByAddress")
    public String searchForZooByAddress(String address){
        return "all-address-page";
    }

    @PostMapping("/zooListByAddress")
    public String postSearchForZooByAddress(@Valid String address, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "all-address-page";
        }
        
        model.addAttribute("zoo", otherService.searchForZooByAddress(address));
        return "zoo-page";
    }
}
