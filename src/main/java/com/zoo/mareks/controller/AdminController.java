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

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.FoodAnimal;
import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.service.IAnimalService;
import com.zoo.mareks.service.IZooService;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/admin")
public class AdminController {
    

    @Autowired
    private IAnimalService animalService;

    @Autowired
    private IZooService zooService;


    @GetMapping("/zoo/createNewZoo")
    public String createNewZoo(Zoo zoo) {
        return "zoo-create-page";
    }

    @PostMapping("/zoo/createNewZoo")
    public String postCreateNewZoo(@Valid Zoo zoo, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "zoo-create-page";
        }
        zooService.createNewZoo(zoo);
        return "redirect:/admin/zoo/list";
    }

    @GetMapping("/zoo/deleteZoo/{id}")
    public String deleteZooById(@PathVariable(name = "id") int id) throws Exception {
        zooService.deleteZooById(id);
        return "redirect:/admin/zoo/list";
    }

    @GetMapping("/zoo/updateZoo/{id}")
    public String updateZooById(@PathVariable(name = "id") int id, Model model) throws Exception{
        Zoo zoo = zooService.getZooById(id);
        model.addAttribute("updateZoo", zoo);
        return "admin-update-zoo-page";
    }

    @PostMapping("/zoo/updateZoo/{id}")
    public String postUpdateZooById(@PathVariable(name = "id") int id, @Valid Zoo zoo, BindingResult result) throws Exception{
        if(result.hasErrors()){return "admin-update-zoo-page";}
        zooService.updateZooById(id, zoo);
        return "redirect:/admin/zoo/list";
    }

    @GetMapping("/animal/add/{zooId}")
    public String addAnimalToZooById(@PathVariable(name = "zooId") int zooId, Animal animal) {
        return "admin-animal-add-page";
    }

    @PostMapping("/animal/add/{zooId}")
    public String postAddAnimalToZooById(@PathVariable(name = "zooId") int zooId, @Valid Animal animal, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "admin-animal-add-page";
        }
        animalService.addAnimalToZooById(animal, zooId);
        return "redirect:/admin/zoo/list/" + zooId;
    }
    
    @GetMapping("/animal/remove/{animalId}/{zooId}")
    public String removeAnimalByIdFromZooById(@PathVariable(name = "animalId") int animalId, @PathVariable(name = "zooId") int zooId) throws Exception {
        animalService.removeAnimalByIdFromZooById(animalId, zooId);
        return "redirect:/admin/zoo/list/" + zooId;
    }

    @GetMapping("/animal/move/{animalId}/{fromZooId}/{toZooId}")
    public String moveAnimalToDiffZooById(@PathVariable(name = "animalId") int animalId, @PathVariable(name = "fromZooId") int fromZooId, @PathVariable(name = "toZooId") int toZooId) throws Exception {
        animalService.moveAnimalToDiffZooById(animalId, fromZooId, toZooId);
        return "redirect:/admin/zoo/list/" + toZooId;
    }

    @GetMapping("/animal/{id}")
    public String getAnimalById(@PathVariable(name = "id") int id, Model model) throws Exception {
        model.addAttribute("animal", animalService.getAnimalById(id));
        return "animal-page";
    }

    @GetMapping("zoo/list")
    public String adminZooList(Model model){
        model.addAttribute("zoos", zooService.getAllZoos());
        return "admin-zoo-list-page";
    }

    @GetMapping("zoo/list/{id}")
    public String adminSingleZooList(@PathVariable(name = "id") int id, Model model) throws Exception{
        model.addAttribute("zoo", zooService.getZooById(id));
        return "admin-zoo-page";
    }
    @GetMapping("/home")
    public String homePage(){
        return "admin-home-page";
    }

    @GetMapping("zoo/buyFood/{id}")
    public String buyFood(@PathVariable(name = "id")int id, FoodAnimal food){
        return "buy-food-page";
    }

    @PostMapping("zoo/buyFood/{id}")
    public String postBuyFood(@PathVariable(name = "id")int id, FoodAnimal food, BindingResult result) throws Exception{
        if(result.hasErrors()){return "buy-food-page";}
        zooService.updateZooFoodCount(id, food);
        return "redirect:/admin/zoo/list/" + id;
        
    }

    @GetMapping("zoo/list/{id}/stats")
    public String zooStats(@PathVariable(name = "id") int id, Model model){
        model.addAttribute("tickets", zooService.calcAverageVisitorCountPerHourInZoo(id));
        model.addAttribute("foodSituation", zooService.calcFoodSuppliesForAnimalsInZoo(id));
        return "admin-zoo-page-stats";
    }
    
}
