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
import webapp.services.AuthService;
import webapp.services.ShopManager;

@Controller
public class ShopController {
  private final ShopManager shopManager;

  @Autowired
  public ShopController(ShopManager shopManager) {
    this.shopManager = shopManager;
  }
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Static pages ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @GetMapping("/about")
  public String about(
    Model model
  ) {
    AuthService auth = new AuthService(shopManager);
    model.addAttribute("auth", auth);

    return "about";
  }

  @GetMapping("/terms")
  public String terms(
    Model model
  ) {
    AuthService auth = new AuthService(shopManager);
    model.addAttribute("auth", auth);

    return "terms";
  }

  @GetMapping("/privacy-policy")
  public String privacyPolicy(
    Model model
  ) {
    AuthService auth = new AuthService(shopManager);
    model.addAttribute("auth", auth);

    return "privacy-policy";
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Home: Products ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @GetMapping("/")
  public String homepage(
    Model model
  ) {
    // Get all products
    Iterable<Product> products = shopManager.getProducts();
    model.addAttribute("products", products);

    AuthService auth = new AuthService(shopManager);
    model.addAttribute("auth", auth);

    return "index";  // Name of the homepage template
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Product Page ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @GetMapping("/product/{productId}")
  public String product(
    @PathVariable("productId") long productId,
    // @Valid Product product,
    // BindingResult result,
    Model model
  ) {
    AuthService auth = new AuthService(shopManager);
    model.addAttribute("auth", auth);

    Product product = shopManager.getProductById(productId);

    if (product == null) {
      return "redirect:/";
    }

    System.out.println(product.getName());

    model.addAttribute("product", product);
    return "product";
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ User Profile ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @GetMapping("/profile")
  public String profile(
    Model model
  ) {
    AuthService auth = new AuthService(shopManager);
    model.addAttribute("auth", auth);

    return "profile";
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Shopping cart ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @GetMapping("/cart")
  public String cart(
    Model model
  ) {
    AuthService auth = new AuthService(shopManager);
    model.addAttribute("auth", auth);

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
}
