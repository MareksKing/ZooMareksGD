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

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.service.IAnimalService;

@Controller
@RequestMapping("/animal")
public class AnimalController {
    
    @Autowired
    private IAnimalService animalService;


    @GetMapping("/add/{zooId}")
    public String addAnimalToZooById(@PathVariable(name = "zooId") int zooId, Animal animal) {
        return "animal-add-page";
    }

    @PostMapping("/add/{zooId}")
    public String postAddAnimalToZooById(@PathVariable(name = "zooId") int zooId, @Valid Animal animal, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "animal-add-page";
        }
        animalService.addAnimalToZooById(animal, zooId);
        return "redirect:/zoo/list" + zooId;
    }
    
    @GetMapping("/remove/{animalId}/{zooId}")
    public String removeAnimalByIdFromZooById(@PathVariable(name = "animalId") int animalId, @PathVariable(name = "zooId") int zooId) throws Exception {
        animalService.removeAnimalByIdFromZooById(animalId, zooId);
        return "redirect:/zoo/list" + zooId;
    }

    @GetMapping("/move/{animalId}/{fromZooId}/{toZooId}")
    public String moveAnimalToDiffZooById(@PathVariable(name = "animalId") int animalId, @PathVariable(name = "fromZooId") int fromZooId, @PathVariable(name = "toZooId") int toZooId) throws Exception {
        animalService.moveAnimalToDiffZooById(animalId, fromZooId, toZooId);
        return "redirect:/zoo/list" + toZooId;
    }

    @GetMapping("/{id}")
    public String getAnimalById(@PathVariable(name = "id") int id, Model model) throws Exception {
        model.addAttribute("animal", animalService.getAnimalById(id));
        return "animal-page";
    }
}
