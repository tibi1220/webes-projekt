package webapp.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    Model model
  ) {
    // Check if product exists
    Product product = shopManager.getProductById(productId);

    if (product == null) {
      return "redirect:/";
    }

    // Check auth
    model.addAttribute("auth", auth);

    if(auth.getIsLoggedIn()) {
      CartItem cartItem = new CartItem();

      cartItem.setProduct(product);
      cartItem.setQuantity(1);
      cartItem.setUser(auth.getUser());

      model.addAttribute("newCartItem", cartItem);
    }


    model.addAttribute("product", product);
    
    Iterable<Review> reviews = shopManager.getProductReviews(productId);

    // Sort reviews by date
    reviews = StreamSupport
      .stream(reviews.spliterator(), false)
      .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
      .collect(Collectors.toList());

    model.addAttribute("reviews", reviews);
    model.addAttribute("newReview", new Review());

    return "product";
  }

  @PostMapping("/product/{productId}/review")
  public String review(
    @PathVariable("productId") long productId,
    @Valid @ModelAttribute Review newReview
  ) {
    Product product = shopManager.getProductById(productId);
    newReview.setProduct(product);
    newReview.setUser(auth.getUser());

    shopManager.addReview(newReview);

    return "redirect:/product/" + productId;
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

    // Sort reviews by date
    reviews = StreamSupport
      .stream(reviews.spliterator(), false)
      .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
      .collect(Collectors.toList());

    model.addAttribute("reviews", reviews);

    Iterable<Order> orders = shopManager.getUserOrders(auth.getUserId());
    
    // Sort orders by date
    orders = StreamSupport
      .stream(orders.spliterator(), false)
      .sorted((a, b) -> b.getOrderDate().compareTo(a.getOrderDate()))
      .collect(Collectors.toList());

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
    // Sort items in alphabetical order based on product name
    cartItems.sort((a, b) -> a.getProduct().getName().compareTo(b.getProduct().getName()));

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

  @PostMapping("/cart/add/{productId}")
  public String addToCart(
    @PathVariable("productId") long productId,
    @Valid @ModelAttribute CartItem newCartItem
  ) {
    shopManager.updateCartItemQuantity(
      auth.getUserId(), 
      productId,
      1,
      true
    );

    return "redirect:/cart";
  }

  @PostMapping("/cart/increment/{productId}")
  public String increment(
    @PathVariable("productId") long productId,
    @Valid @ModelAttribute CartItem cartItem
  ) {
    shopManager.updateCartItemQuantity(
      auth.getUserId(), 
      productId,
      1,
      true
    );

    return "redirect:/cart";
  }

  @PostMapping("/cart/decrement/{productId}")
  public String decrement(
    @PathVariable("productId") long productId,
    @Valid @ModelAttribute CartItem cartItem
  ) {
    shopManager.updateCartItemQuantity(
      auth.getUserId(), 
      productId,
      -1,
      true
    );

    return "redirect:/cart";
  }

  @PostMapping("/cart/update/{productId}/{quantity}")
  public String update(
    @PathVariable("productId") long productId,
    @PathVariable("quantity") int quantity
  ) {
    shopManager.updateCartItemQuantity(
      auth.getUserId(), 
      productId,
      quantity,
      false
    );

    return "redirect:/cart";
  }


  @PostMapping("/cart/remove/{productId}")
  public String deleteCartItem(
    @PathVariable("productId") long productId,
    @Valid @ModelAttribute CartItem cartItem
  ) {
    shopManager.updateCartItemQuantity(
      auth.getUserId(), 
      productId,
      0,
      false
    );

    return "redirect:/cart";
  }

  @PostMapping("/cart/remove-all")
  public String deleteAllCartItem() {
    shopManager.deleteAllCartItems(auth.getUserId());

    return "redirect:/cart";
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Order ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @PostMapping("/order")
  public String order() {
    shopManager.placeOrder(auth.getUserId());

    return "redirect:/profile";
  }
}
