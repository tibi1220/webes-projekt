package webapp.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import webapp.entities.CartItem;
import webapp.entities.Order;
import webapp.entities.Product;
import webapp.entities.Review;
import webapp.services.AuthService;
import webapp.services.ShopManager;

@Controller
public class ShopController {
  private final ShopManager shopManager;
  private final AuthService auth;

  @Autowired
  public ShopController(
    ShopManager shopManager,
    AuthService auth
  ) {
    this.shopManager = shopManager;
    this.auth = auth;
  }
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Static pages ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @GetMapping("/about")
  public String about(
    Model model
  ) {
    model.addAttribute("auth", auth);

    return "about";
  }

  @GetMapping("/terms")
  public String terms(
    Model model
  ) {
    model.addAttribute("auth", auth);

    return "terms";
  }

  @GetMapping("/privacy-policy")
  public String privacyPolicy(
    Model model
  ) {
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
    model.addAttribute("auth", auth);

    Product product = shopManager.getProductById(productId);

    if (product == null) {
      return "redirect:/";
    }

    model.addAttribute("product", product);
    
    Iterable<Review> reviews = shopManager.getProductReviews(productId);

    model.addAttribute("reviews", reviews);
    model.addAttribute("newReview", new Review());

    return "product";
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ User Profile ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @GetMapping("/profile")
  public String profile(
    Model model
  ) {
    model.addAttribute("auth", auth);

    Iterable<Review> reviews = shopManager.getUserReviews(auth.getUserId());
    model.addAttribute("reviews", reviews);

    Iterable<Order> orders = shopManager.getUserOrders(auth.getUserId());
    model.addAttribute("orders", orders);
    
    List<BigDecimal> totals = StreamSupport
      .stream(orders.spliterator(), false)
      .map(order -> order.getOrderItems().stream()
        .map(orderItem -> orderItem.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add)
      )
      .collect(Collectors.toList());
    model.addAttribute("totals", totals);

    return "profile";
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Shopping cart ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @GetMapping("/cart")
  public String cart(
    Model model
  ) {
    model.addAttribute("auth", auth);
    long userId = auth.getUserId();

    List<CartItem> cartItems = shopManager.getCartItemsByUserId(userId);
    model.addAttribute("cartItems", cartItems);

    BigDecimal total = cartItems
      .stream()
      .map(cartItem -> cartItem
        .getProduct()
        .getPrice()
        .multiply(BigDecimal.valueOf(cartItem.getQuantity()))
      )
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    model.addAttribute("total", total);

    return "cart";
  }
}