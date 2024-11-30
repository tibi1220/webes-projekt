package webapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.entities.*;
import webapp.repositories.*;

@Service
public class ShopManager {
  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  private final CartItemRepository cartItemRepository;
  private final ReviewRepository reviewRepository;
  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;

  @Autowired
  public ShopManager(
    UserRepository userRepository,
    ProductRepository productRepository,
    CartItemRepository cartItemRepository,
    ReviewRepository reviewRepository,
    OrderRepository orderRepository,
    OrderItemRepository orderItemRepository
  ) {
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.cartItemRepository = cartItemRepository;
    this.reviewRepository = reviewRepository;
    this.orderRepository = orderRepository;
    this.orderItemRepository = orderItemRepository;
  }

  // User
  public Optional<User> getUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  // Product
  public Iterable<Product> getProducts() {
    return productRepository.findAll();
  }
  public Product getProductById(Long id) {
    return productRepository.findById(id).orElse(null);
  }

  // CartItem
  public List<CartItem> getCartItemsByUserId(long userId) {
    return cartItemRepository.findByUser_UserId(userId);
  }

  // Review
  public Iterable<Review> getReviews() {
    return reviewRepository.findAll();
  }

  // Order
  public Iterable<Order> getOrders() {
    return orderRepository.findAll();
  }

  // OrderItem
  public Iterable<OrderItem> getOrderItems() {
    return orderItemRepository.findAll();
  }
}
