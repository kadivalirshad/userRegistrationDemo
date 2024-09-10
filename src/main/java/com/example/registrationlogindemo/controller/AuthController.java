package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

	private UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("index")
	public String home() {
		return "index";
	}

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	// handler method to handle user registration request
	@GetMapping("register")
	public String showRegistrationForm(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register";
	}

	// handler method to handle register user form submit request
	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
		User existing = userService.findByEmail(user.getEmail());
		if (existing != null) {
			result.rejectValue("email", null, "There is already an account registered with that email");
		}
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}

		userService.saveUser(user);
		return "redirect:/register?success";
	}

	@GetMapping("/users")
	public String listRegisteredUsers(Model model) {
		List<UserDto> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		UserDto user = userService.findById(id);
		model.addAttribute("user", user);
		return "update-user";
	}

	@GetMapping("view/{id}")
	public String viewUserdetails(@PathVariable("id") long id, Model model) {
		UserDto user = userService.findById(id);
		model.addAttribute("user", user);
		return "viewDetails";
	}

	@PostMapping("update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid @ModelAttribute("user") UserDto user,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			userService.saveUser(user);
			return "update-user";
		}
		userService.updateUser(user);
		model.addAttribute("users", userService.findAllUsers());
		return "redirect:/users";
	}

	@GetMapping("delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		boolean user = userService.deleteById(id);
		if (user) {
			model.addAttribute("success", "User Delete SuccessFuly");
		} else {
			model.addAttribute("success", "oops ! Something went wrong");
		}
		model.addAttribute("users", userService.findAllUsers());
		return "redirect:/users";
	}

}
