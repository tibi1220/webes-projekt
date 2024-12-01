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

  public List<CartItem> getUserCartItems(long userId) {
    return cartItemRepository.findByUser_UserId(userId);
  }

  public void updateCartItems(List<CartItem> cartItem) {
    cartItemRepository.saveAll(cartItem);
  }
  

  // Delete all items of a user
  public void deleteAllCartItems(long userId) {
    cartItemRepository.deleteByUser_UserId(userId);
  }

  // Delete a specific item of a user
  public void deleteCartItem(long userId, long productId) {
    cartItemRepository.deleteByUser_UserIdAndProduct_ProductId(userId, productId);
  }
}
