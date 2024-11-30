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
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(
    @Valid User user,
    BindingResult bindingResult, 
    @RequestParam("confirmPassword") String confirmPassword,
    Model model
  ) {
    if (!user.getPassword().equals(confirmPassword)) {
      bindingResult.rejectValue("password", "error.user", "Passwords do not match");
    }

    if (bindingResult.hasErrors()) {
      model.addAttribute("user", user);
      return "register";
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userService.registerUser(user);

    return "redirect:/login"; 
  }
}
