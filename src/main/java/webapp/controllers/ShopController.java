package webapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

  @GetMapping("/")
  public String homepage(Model model) {
    // Check if the user is authenticated
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      String username = authentication.getName();  // Get the logged-in user's username
      model.addAttribute("username", username);  // Pass the username to the model
    }
    return "index";  // Name of the homepage template
  }
}
