package webapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import webapp.entities.CartItem;
import webapp.entities.Product;
import webapp.entities.User;
import webapp.services.ShopManager;

@Controller
public class ShopController {
  private final ShopManager shopManager;

  @Autowired
  public ShopController(ShopManager shopManager) {
    this.shopManager = shopManager;
  }

  @GetMapping("/about")
  public String about() {
    return "about";
  }

  @GetMapping("/terms")
  public String terms() {
    return "terms";
  }

  @GetMapping("/privacy-policy")
  public String privacyPolicy() {
    return "privacy-policy";
  }

  @GetMapping("/")
  public String homepage(
    Model model
  ) {
    // Get all products
    Iterable<Product> products = shopManager.getProducts();
    model.addAttribute("products", products);

    System.out.println(products);

    // Auth
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      String username = authentication.getName();
      boolean isLoggedIn = username != "anonymousUser";
      
      model.addAttribute("username", isLoggedIn ? username : null);  // Pass the username to the model
    }
    return "index";  // Name of the homepage template
  }

  @GetMapping("/cart")
  public String cart(
    Model model
  ) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    Optional<User> user = shopManager.getUserByUsername(username);

    if (user.isEmpty()) {
      return "redirect:/";
    } else {
      List<CartItem> cartItems = shopManager.getCartItemsByUserId(user.get().getUserId());

      System.out.println(
        "\n\n\n --------- Debugging cartItems --------- \n\n\n" +
        cartItems.get(0).getQuantity()
      );
    }

    return "cart";
  }

  @GetMapping("/product/{productId}")
  public String product(
    @PathVariable("productId") long productId,
    // @Valid Product product,
    // BindingResult result,
    Model model
  ) {
    Product product = shopManager.getProductById(productId);

    if (product == null) {
      return "redirect:/";
    }

    System.out.println(product.getName());

    model.addAttribute("product", product);
    return "product";
  }
}
