package de.Hashem.bigbazar.controller;

import de.Hashem.bigbazar.entity.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import de.Hashem.bigbazar.repository.UserRepo;

/*
 * Klasse für Api und kann man ein besondere Kunde finden und POST PUT DELETE 
 * TODO
*/

@RestController
public class ApiController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/userss")
    public List<Customer> getUsers() {

        return userRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody Customer user){

        userRepo.save(user);
        return "save ..";
    }

    @PutMapping(value ="/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody Customer user){
        Customer updateUser1 = userRepo.findById(id).get();
        updateUser1.setEmail(user.getEmail());
        userRepo.save(updateUser1);
        return "update";
    }

    @DeleteMapping(value ="/delete/{id}")
    public String deleteUser(@PathVariable long id){
        Customer deleteUser1 = userRepo.findById(id).get();
        userRepo.delete(deleteUser1);
        return "delete";
    }
}