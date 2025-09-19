package com.mtd.ecom_server.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtd.ecom_server.exceptions.ResourceNotFoundException;
import com.mtd.ecom_server.models.User;
import com.mtd.ecom_server.repos.UserRepo;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepo userRepo;

	private final static Logger log = LoggerFactory.getLogger(UserController.class);

	@Tag(name = "Get All Users")
	@GetMapping("/all")
	public List<User> getAllUsers() {
		log.info("Fetching all users");
		return userRepo.findAll();
	}

	@Tag(name = "Get User by ID")
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable String id) {
		log.info("Fetching user with ID: " + id);
		Optional<User> findUser = userRepo.findById(id);
		if (findUser.isEmpty()) {
			log.error("User Not Found with ID: " + id);
			throw new ResourceNotFoundException("User Not Found");
		}
		return findUser.get();
	}

	@Tag(name = "Get User by Email")
	@GetMapping("/email/{email}")
	public User getUserByEmail(@PathVariable String email) {
		log.info("Fetching user with email: " + email);
		Optional<User> findUser = userRepo.findByEmail(email);
		if (findUser.isEmpty()) {
			log.error("User Not Found with email: " + email);
			throw new ResourceNotFoundException("User Not Found");
		}
		return findUser.get();
	}

	@Tag(name = "Add New User")
	@PostMapping("/add")
	public User addUser(@RequestBody User newUser) {
		log.info("Adding new user: " + newUser);
		return userRepo.save(newUser);
	}

	@Tag(name = "Delete User")
	@DeleteMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable String id) {
		Optional<User> findUser = userRepo.findById(id);
		if (findUser.isEmpty()) {
			log.error("User Not Found with ID: " + id);
			throw new ResourceNotFoundException("User Not Found");
		}
		userRepo.deleteById(id);
		return "User Deleted";
	}

	@Tag(name = "Edit User")
	@PutMapping("/user/edit/{id}")
	public User editUser(@PathVariable String id, @RequestBody User newUser) {
		User findUser = userRepo.findById(id).orElseThrow(() -> {
			log.error("User Not Found with ID: " + id);
			return new ResourceNotFoundException("User Not Found");
		});

		findUser.setName(newUser.getName());
		findUser.setEmail(newUser.getEmail());
		findUser.setPassword(newUser.getPassword());
		findUser.setStreet(newUser.getStreet());
		findUser.setCity(newUser.getCity());
		findUser.setZip(newUser.getZip());

		return userRepo.save(findUser);
	}
}