package webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp.entities.User;
import webapp.services.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {

  @Autowired
  private UserService userService;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "register";  // Display the registration form
  }

  @PostMapping("/register")
  public String registerUser(@Valid User user, BindingResult bindingResult, 
                             @RequestParam("confirmPassword") String confirmPassword, Model model) {
    // Check if passwords match
    if (!user.getPassword().equals(confirmPassword)) {
      bindingResult.rejectValue("password", "error.user", "Passwords do not match");
    }

    // If there are validation errors, return to the registration form
    if (bindingResult.hasErrors()) {
      model.addAttribute("user", user);
      return "register";
    }

    // Encode the password before saving
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // Save the user to the database
    userService.registerUser(user);
    
    return "redirect:/login";  // Redirect to the login page after successful registration
  }
}
