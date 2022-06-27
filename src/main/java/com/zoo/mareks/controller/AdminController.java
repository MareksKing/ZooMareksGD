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
import com.zoo.mareks.models.FoodAnimal;
import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.service.IAnimalService;
import com.zoo.mareks.service.IZooService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAnimalService animalService;

    @Autowired
    private IZooService zooService;

    /**
     * This function is a GET request that returns a string that is the name of the
     * html page that will be
     * rendered
     * 
     * @param zoo This is the name of the Zoo object that will be created.
     * @return A string.
     */
    @GetMapping("/zoo/createNewZoo") //localhost:8080/admin/zoo/createNewZoo
    public String createNewZoo(Zoo zoo) {
        return "zoo-create-page";
    }

    /**
     * It takes a Zoo object, validates it, and if it's valid, it saves it to the
     * database
     * 
     * @param zoo    This is the object that will be used to store the data that the
     *               user enters in the form.
     * @param result The BindingResult must come right after the model object that
     *               is validated or else
     *               Spring fails to validate the object and throws an exception.
     * @return A String
     */
    @PostMapping("/zoo/createNewZoo")
    public String postCreateNewZoo(@Valid Zoo zoo, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "zoo-create-page";
        }
        zooService.createNewZoo(zoo);
        return "redirect:/admin/zoo/list";
    }

    /**
     * It deletes a zoo by id
     * 
     * @param id the id of the zoo to be deleted
     * @return A redirect to the list of zoos.
     */
    @GetMapping("/zoo/deleteZoo/{id}") //localhost:8080/admin/zoo/deleteZoo/{id}
    public String deleteZooById(@PathVariable(name = "id") int id) throws Exception {
        zooService.deleteZooById(id);
        return "redirect:/admin/zoo/list";
    }

    /**
     * This function is used to update the zoo details
     * 
     * @param id    the id of the zoo to be updated
     * @param model This is the model object that is used to pass data from the
     *              controller to the view.
     * @return The updateZooById method is returning the zoo object.
     */
    @GetMapping("/zoo/updateZoo/{id}") //localhost:8080/admin/zoo/updateZoo/{id}
    public String updateZooById(@PathVariable(name = "id") int id, Model model) throws Exception {
        Zoo zoo = zooService.getZooById(id);
        model.addAttribute("updateZoo", zoo);
        return "admin-update-zoo-page";
    }

    /**
     * It takes the id of the zoo to be updated, the new zoo object, and the result
     * of the form validation.
     * If the form validation fails, it returns the admin-update-zoo-page. If the
     * form validation passes,
     * it updates the zoo and redirects to the admin-zoo-list-page
     * 
     * @param id     the id of the zoo to be updated
     * @param zoo    This is the object that will be used to update the Zoo object
     *               in the database.
     * @param result BindingResult
     * @return A String
     */
    @PostMapping("/zoo/updateZoo/{id}")
    public String postUpdateZooById(@PathVariable(name = "id") int id, @Valid Zoo zoo, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return "admin-update-zoo-page";
        }
        zooService.updateZooById(id, zoo);
        return "redirect:/admin/zoo/list";
    }

    /**
     * "This function takes in a zooId and an animal object and returns a string."
     * 
     * The @GetMapping annotation tells Spring that this function will be called
     * when a GET request is made
     * to the URL /animal/add/{zooId}. The @PathVariable annotation tells Spring
     * that the zooId variable
     * will be passed in as a parameter to the function. The animal object will be
     * passed in as a parameter
     * to the function as well. The function returns a string, which is the name of
     * the page that will be
     * rendered
     * 
     * @param zooId  the id of the zoo that the animal will be added to
     * @param animal the object that will be passed to the view
     * @return A String
     */
    @GetMapping("/animal/add/{zooId}") //localhost:8080/admin/animal/add/{zooId}
    public String addAnimalToZooById(@PathVariable(name = "zooId") int zooId, Animal animal) {
        return "admin-animal-add-page";
    }

    /**
     * It takes an animal object and a zooId, validates the animal object, and if
     * it's valid, adds the
     * animal to the zoo with the given zooId
     * 
     * @param zooId  the id of the zoo that the animal is being added to
     * @param animal the object that will be passed to the service layer
     * @param result BindingResult
     * @return A String
     */
    @PostMapping("/animal/add/{zooId}")
    public String postAddAnimalToZooById(@PathVariable(name = "zooId") int zooId, @Valid Animal animal,
            BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "admin-animal-add-page";
        }
        animalService.addAnimalToZooById(animal, zooId);
        return "redirect:/admin/zoo/list/" + zooId;
    }

    /**
     * It removes an animal from a zoo
     * 
     * @param animalId the id of the animal to be removed
     * @param zooId    the id of the zoo in which the animal is being held
     * @return A redirect to the admin/zoo/list/{zooId} page.
     */
    @GetMapping("/animal/remove/{animalId}/{zooId}") //localhost:8080/admin/animal/remove/{animalId}/{zooId}
    public String removeAnimalByIdFromZooById(@PathVariable(name = "animalId") int animalId,
            @PathVariable(name = "zooId") int zooId) throws Exception {
        animalService.removeAnimalByIdFromZooById(animalId, zooId);
        return "redirect:/admin/zoo/list/" + zooId;
    }

    /**
     * It takes an animalId, a fromZooId, and a toZooId, and moves the animal from
     * the fromZooId to the
     * toZooId
     * 
     * @param animalId  the id of the animal to be moved
     * @param fromZooId the zoo that the animal is currently in
     * @param toZooId   the id of the zoo that the animal is being moved to
     * @return A redirect to the admin/zoo/list/{toZooId} page.
     */
    @GetMapping("/animal/move/{animalId}/{fromZooId}/{toZooId}") //localhost:8080/admin/animal/move/{animalId}/{fromZooId}/{toZooId}
    public String moveAnimalToDiffZooById(@PathVariable(name = "animalId") int animalId,
            @PathVariable(name = "fromZooId") int fromZooId, @PathVariable(name = "toZooId") int toZooId)
            throws Exception {
        animalService.moveAnimalToDiffZooById(animalId, fromZooId, toZooId);
        return "redirect:/admin/zoo/list/" + toZooId;
    }

    /**
     * This function takes an id from the url, uses it to get an animal from the
     * database, and then returns
     * the animal-page.html file
     * 
     * @param id    the id of the animal to be retrieved
     * @param model This is the model object that is used to pass data from the
     *              controller to the view.
     * @return A String
     */
    @GetMapping("/animal/{id}") //localhost:8080/admin/animal/{id}
    public String getAnimalById(@PathVariable(name = "id") int id, Model model) throws Exception {
        model.addAttribute("animal", animalService.getAnimalById(id));
        return "animal-page";
    }

    /**
     * This function is called when the user navigates to the URL /zoo/list. It then
     * calls the
     * zooService.getAllZoos() function to get a list of all the zoos in the
     * database. It then adds this
     * list to the model and returns the admin-zoo-list-page
     * 
     * @param model This is the model object that is used to pass data to the view.
     * @return A list of all the zoos in the database.
     */
    @GetMapping("zoo/list") //localhost:8080/admin/zoo/list
    public String adminZooList(Model model) {
        model.addAttribute("zoos", zooService.getAllZoos());
        return "admin-zoo-list-page";
    }

    /**
     * This function is used to get a single zoo by id and display it on the
     * admin-zoo-page
     * 
     * @param id    the id of the zoo
     * @param model The model is an object that holds the data that you want to pass
     *              to the view.
     * @return A single zoo object.
     */
    @GetMapping("zoo/list/{id}") //localhost:8080/admin/zoo/list/{id}
    public String adminSingleZooList(@PathVariable(name = "id") int id, Model model) throws Exception {
        model.addAttribute("zoo", zooService.getZooById(id));
        return "admin-zoo-page";
    }

    /**
     * It returns the site for the administrator
     * 
     * @return The admin-home-page.html file
     */
    @GetMapping("/home") //localhost:8080/admin/home
    public String homePage() {
        return "admin-home-page";
    }

    /**
     * The function takes in an id and a FoodAnimal object, and returns a string
     * 
     * @param id   the id of the animal
     * @param food the object that will be used to store the data that the user
     *             enters in the form.
     * @return A string
     */
    @GetMapping("zoo/buyFood/{id}") //localhost:8080/admin/zoo/buyFood/{id}
    public String buyFood(@PathVariable(name = "id") int id, FoodAnimal food) {
        return "admin-buy-food-page";
    }

    /**
     * The function takes in a food object and an id, and then updates the zoo's
     * food count
     * 
     * @param id     the id of the zoo
     * @param food   the object that is being passed to the method
     * @param result BindingResult
     * @return A string
     */
    @PostMapping("zoo/buyFood/{id}")
    public String postBuyFood(@PathVariable(name = "id") int id, FoodAnimal food, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return "admin-buy-food-page";
        }
        zooService.updateZooFoodCount(id, food);
        return "redirect:/admin/zoo/list/" + id;

    }

    /**
     * This function is used to calculate the average visitor count per hour in a
     * zoo and the food supplies
     * for animals in a zoo
     * 
     * @param id    the id of the zoo
     * @param model the model object that is used to pass data to the view.
     * @return A string
     */
    @GetMapping("zoo/list/{id}/stats") //localhost:8080/admin/zoo/list/{id}/stats
    public String zooStats(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("tickets", zooService.calcAverageVisitorCountPerHourInZoo(id));
        model.addAttribute("foodSituation", zooService.calcFoodSuppliesForAnimalsInZoo(id));
        return "admin-zoo-page-stats";
    }

}
