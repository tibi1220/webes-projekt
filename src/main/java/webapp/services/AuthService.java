package webapp.services;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import webapp.entities.User;

public class AuthService {
  private boolean isLoggedIn;
  private int cartItems;
  private String username;
  private long userId;
  private ShopManager shopManager;

  public AuthService(
    ShopManager shopManager
  ) {
    this.shopManager = shopManager;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    String username = authentication.getName();

    if (authentication != null && authentication.isAuthenticated() && username != "anonymousUser") {
      this.username = username;
      this.isLoggedIn = true;

      // Get user
      Optional<User> user = this.shopManager.getUserByUsername(username);

      if (user.isPresent()) {
        this.userId = user.get().getUserId();
        this.cartItems = shopManager.getCartItemsByUserId(this.userId).size();
      } else {
        this.userId = 0;
        this.cartItems = 0;
      }
    } else {
      this.username = null;
      this.isLoggedIn = false;
      this.cartItems = 0;
      this.userId = 0;
    }
  }

  // Getters
  public boolean getIsLoggedIn() {
    return this.isLoggedIn;
  }
  public int getCartItems() {
    return this.cartItems;
  }
  public String getUsername() {
    return this.username;
  }
  public long getUserId() {
    return this.userId;
  }
}
