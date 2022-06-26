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

    /**
     * This function takes an id as a parameter, and returns a zoo object with that
     * id
     * 
     * @param id    the id of the zoo
     * @param model The model is an object that stores data that will be displayed
     *              in the view.
     * @return A zoo object
     */
    @GetMapping("/{id}") //localhost:8080/zoo/{id}
    public String getZooById(@PathVariable(name = "id") int id, Model model) throws Exception {
        model.addAttribute("zoo", zooService.getZooById(id));
        return "user-zoo-page";
    }

    /**
     * It gets all the zoos from the database and puts them in a list
     * 
     * @param model The model is an object that holds the data that you want to pass
     *              to the view.
     * @return A list of all the zoos in the database.
     */
    @GetMapping("/list") //localhost:8080/zoo/list/
    public String getAllZoos(Model model) throws Exception {
        model.addAttribute("zoos", zooService.getAllZoos());
        return "user-zoo-list-page";
    }

    /**
     * It takes an id, finds the zoo with that id, and then returns a list of
     * animals that are in that zoo
     * 
     * @param id    the id of the zoo
     * @param model The model is an object that holds data that you want to pass to
     *              the view.
     * @return A list of animals
     */
    @GetMapping("/list/{id}") //localhost:8080/zoo/list/{id}
    public String getAllAnimalsByZooId(@PathVariable(name = "id") int id, Model model) throws Exception {
        model.addAttribute("animals", zooService.getAllAnimalsByZooById(id));
        return "user-animal-list-page";
    }

    /**
     * It takes the id of the user and a ticket object as parameters and returns a
     * string
     * 
     * @param id     the id of the user
     * @param ticket the ticket object that will be used to create a new ticket
     * @return A string.
     */
    @GetMapping("/{id}/buyNewTicket") //localhost:8080/zoo/{id}/buyNewTicket
    public String buyNewTicket(@PathVariable(name = "id") int id, Ticket ticket) {
        return "buy-ticket-page";
    }

    /**
     * The function takes a ticket object and a binding result object as parameters.
     * If the binding result
     * object has errors, the function returns the buy-ticket-page. Otherwise, the
     * function calls the
     * buyNewTicket function in the zooService and returns the list page
     * 
     * @param ticket the ticket object that is being created
     * @param result The BindingResult must come right after the model object that
     *               is validated or else
     *               Spring fails to validate the object and throws an exception.
     * @return A string
     */
    @PostMapping("/{id}/buyNewTicket")
    public String postBuyNewTicket(@PathVariable(name = "id") int id, @Valid Ticket ticket, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "buy-ticket-page";
        }
        zooService.buyNewTicket(ticket, id);
        return "redirect:/zoo/list";
    }
}
