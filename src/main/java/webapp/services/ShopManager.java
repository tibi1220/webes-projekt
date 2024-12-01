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
  public User getUserById(long id) {
    return userRepository.findById(id).orElse(null);
  }
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
  public void addToCart(CartItem cartItem) {
    cartItemRepository.save(cartItem);
  }
  public void updateCartItemQuantity(long userId, long productId, int quantity, boolean isIncrement) {
    CartItem item = cartItemRepository.findByUser_UserIdAndProduct_ProductId(userId, productId);

    if (item != null) {
      // Check current quantity
      int q = item.getQuantity();

      // Only allow increment if the quantity is less than 10
      if (isIncrement && q + quantity > 10) {
        return;
      }

      // Only allow decrement if the quantity is greater than 1
      if (isIncrement && q + quantity < 1) {
        return;
      }

      // if not incrementing, and the quantity is 0, remove the item
      if (!isIncrement && quantity == 0) {
        cartItemRepository.delete(item);
        return;
      }

      if (isIncrement) {
        item.setQuantity(item.getQuantity() + quantity);
      } else {
        item.setQuantity(quantity);
      }
      cartItemRepository.save(item);

      return;
    }

    CartItem newItem = new CartItem();
    newItem.setUser(userRepository.findById(userId).orElse(null));
    newItem.setProduct(productRepository.findById(productId).orElse(null));
    newItem.setQuantity(quantity);
    cartItemRepository.save(newItem);
  }
  public void setCartItemQuantity(int quantity, long userId, long cartItemId) {
    CartItem item = cartItemRepository.findByCartItemIdAndUser_UserId(cartItemId, userId);
    item.setQuantity(quantity);
    cartItemRepository.save(item);
  }
  public CartItem getCartItem(long userId, long productId) {
    return cartItemRepository.findByUser_UserIdAndProduct_ProductId(userId, productId);
  }
  public List<CartItem> getCartItemsByUserId(long userId) {
    return cartItemRepository.findByUser_UserId(userId);
  }
  public void updateCartItems(List<CartItem> cartItems) {
    cartItemRepository.saveAll(cartItems);
  }
  public void deleteAllCartItems(long userId) {
    List<CartItem> cartItems = cartItemRepository.findByUser_UserId(userId);
    cartItemRepository.deleteAll(cartItems);
  }

  // Review
  public Iterable<Review> getProductReviews(long productId) {
    return reviewRepository.findByProduct_ProductId(productId);
  }
  public Iterable<Review> getUserReviews(long userId) {
    return reviewRepository.findByUser_UserId(userId);
  }
  public void addReview(Review review) {
    reviewRepository.save(review);
  }

  // Order
  public Iterable<Order> getUserOrders(long userId) {
    return orderRepository.findByUser_UserId(userId);
  }
  public void placeOrder(long userId) {
    List<CartItem> cartItems = cartItemRepository.findByUser_UserId(userId);

    Order order = new Order();
    order.setUser(userRepository.findById(userId).orElse(null));
    orderRepository.save(order);

    for (CartItem item : cartItems) {
      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(order);
      orderItem.setProduct(item.getProduct());
      orderItem.setQuantity(item.getQuantity());
      orderItemRepository.save(orderItem);
    }

    cartItemRepository.deleteAll(cartItems);
  }

  // OrderItem
  public Iterable<OrderItem> getOrderItems() {
    return orderItemRepository.findAll();
  }
}
