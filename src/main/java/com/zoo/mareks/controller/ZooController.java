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

import com.zoo.mareks.models.Ticket;
import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.service.IZooService;

@Controller
@RequestMapping("/zoo")
public class ZooController {
    
    @Autowired
    private IZooService zooService;

    @GetMapping("/{id}")
    public String getZooById(@PathVariable(name = "id") int id, Model model) throws Exception {
        model.addAttribute("zoo", zooService.getZooById(id));
        return "user-zoo-page";
    }

    @GetMapping("/list")
    public String getAllZoos(Model model) throws Exception {
        model.addAttribute("zoos", zooService.getAllZoos());
        return "user-zoo-list-page";
    }

    @GetMapping("/list/{id}")
    public String getAllAnimalsByZooId(@PathVariable(name = "id") int id, Model model) throws Exception {
        model.addAttribute("animals", zooService.getAllAnimalsByZooById(id));
        return "user-animal-list-page";
    }

    @GetMapping("/{id}/buyNewTicket")
    public String buyNewTicket(@PathVariable(name = "id") int id, Ticket ticket){
        return "buy-ticket-page";
    }

    @PostMapping("/{id}/buyNewTicket")
    public String postBuyNewTicket(@Valid Ticket ticket, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "buy-ticket-page";
        }
        zooService.buyNewTicket(ticket);
        return "redirect:/zoo/list";
    }
}
