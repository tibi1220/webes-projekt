package webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.entities.CartItem;
import webapp.repositories.CartItemRepository;

@Service
public class CartItemService {
  
  @Autowired
  private CartItemRepository cartItemRepository;

  public int getTotalItemsInCart(long userId) {
    List<CartItem> cartItems = cartItemRepository.findByUser_UserId(userId);
    return cartItems
      .stream()
      .mapToInt(CartItem::getQuantity)
      .sum();
  }
}
