package webapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
  @GetMapping("/login")
  public String showLoginForm() {
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
    
    // Show login form otherwise
    return "login";
  }
}
