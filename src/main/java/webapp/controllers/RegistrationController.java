package webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    // Check if user is already logged in
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (
      authentication != null &&
      authentication.isAuthenticated() &&
      !"anonymousUser".equals(authentication.getName())
    ) {
      // Redirect to home page if user is already logged in
      return "redirect:/";
    }

    // Show registration form otherwise
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(
    @Valid User user,
    BindingResult bindingResult, 
    Model model
  ) {
    // Check if username already taken
    if(userService.usernameExists(user.getUsername())) {
      bindingResult.rejectValue(
        "username",
        "error.username",
        "Username already taken"
      );
    }

    // Check if email already taken
    if(userService.emailExists(user.getEmail())) {
      bindingResult.rejectValue(
        "email",
        "error.user",
        "Email already taken"
      );
    }

    if (bindingResult.hasErrors()) {
      // Debug user
      System.out.println(
        "\n\n\nUser:\n\n\n" +
        user.getEmail() + " " +
        user.getUsername() + " " +
        user.getPassword() + " " +
        bindingResult.getAllErrors() + "\n\n\n"
      );

      model.addAttribute("user", user);
      return "register";
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userService.registerUser(user);

    return "redirect:/login"; 
  }
}
