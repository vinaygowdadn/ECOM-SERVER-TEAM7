 package com.bootA.ecom_server.controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bootA.ecom_server.exception.ResourceNotFoundException;
import com.bootA.ecom_server.models.user;
import com.bootA.ecom_server.repos.UserRepo;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger Log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepo userRepo;

    
    @GetMapping("/all")
    public List<user> getAllUsers() {
        Log.info("Fetching all users");
        return userRepo.findAll();
    }

    
    @PostMapping("/register")
    public user registerUser(@RequestBody user newUser) {
        Log.info("Registering new user: " + newUser.getEmail());
    
        return userRepo.save(newUser);
    }

    
    @GetMapping("/{id}")
    public user getUserById(@PathVariable String id) {
        Log.info("Fetching user with ID: " + id);
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    
    @PutMapping("/edit/{id}")
    public user editUser(@PathVariable String id, @RequestBody user updatedUser) {
        Log.info("Updating user with ID: " + id);
        user user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        
        user.setStreet(updatedUser.getStreet());
        user.setCity(updatedUser.getCity());
        user.setZip(updatedUser.getZip());

        return userRepo.save(user);
    }

   
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        Log.info("Deleting user with ID: " + id);
        if (!userRepo.existsById(id)) {
             throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepo.deleteById(id);
        return "User with ID " + id + " has been deleted.";
}
}
