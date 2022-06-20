package com.zoo.mareks.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.service.IZooService;

@Controller
@RequestMapping("/zoo")
public class ZooController {
    
    @Autowired
    private IZooService zooService;

    @GetMapping("/createNewZoo")
    public String createNewZoo(Zoo Zoo) {
        return "zoo-create-page";
    }

    @PostMapping("/createNewZoo")
    public String postCreateNewZoo(@Valid Zoo Zoo, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "zoo-create-page";
        }
        zooService.createNewZoo(Zoo);
        return "redirect:/zoo/list";
    }

    @GetMapping("/deleteZoo/{id}")
    public String deleteZooById(@PathVariable(name = "id") int id) throws Exception {
        zooService.deleteZooById(id);
        return "redirect:/zoo/list";
    }

    @GetMapping("/{id}")
    public String getZooById(@PathVariable(name = "id") int id, Model model) throws Exception {
        model.addAttribute("zoo", zooService.getZooById(id));
        return "zoo-page";
    }

    @GetMapping("/list")
    public String getAllZoos(Model model) throws Exception {
        model.addAttribute("zoos", zooService.getAllZoos());
        return "zoo-list-page";
    }

    @GetMapping("/list/{id}")
    public String getAllAnimalsByZooId(@PathVariable(name = "id") int id, Model model) throws Exception {
        model.addAttribute("animals", zooService.getAllAnimalsByZooById(id));
        return "animal-list-page";
    }
}
