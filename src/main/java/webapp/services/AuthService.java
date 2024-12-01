package webapp.services;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import webapp.entities.User;

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthService {

  private final ShopManager shopManager;
  private final CartItemService cartItemService;

  private boolean isLoggedIn;
  private int cartItems;
  private String username;
  private long userId;
  private User user;

  public AuthService(ShopManager shopManager, CartItemService cartItemService) {
    this.shopManager = shopManager;
    this.cartItemService = cartItemService;
    initializeUserContext();
  }

  private void initializeUserContext() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (
      authentication != null &&
      authentication.isAuthenticated() &&
      !"anonymousUser".equals(authentication.getName())
    ) {
      this.username = authentication.getName();
      this.isLoggedIn = true;

      Optional<User> user = shopManager.getUserByUsername(username);
      this.user = user.orElse(null);

      if (user.isPresent()) {
        this.userId = user.get().getUserId();
        this.cartItems = cartItemService.getTotalItemsInCart(this.userId);
      } else {
        clearUserContext();
      }
    } else {
      clearUserContext();
    }
  }

  private void clearUserContext() {
    this.username = null;
    this.isLoggedIn = false;
    this.cartItems = 0;
    this.userId = 0;
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

  public User getUser() {
    return this.user;
  }
}
