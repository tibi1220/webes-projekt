package webapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webapp.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
  CartItem findByUser_UserIdAndProduct_ProductId(long userId, long productId);
  CartItem findByCartItemIdAndUser_UserId(long cartItemId, long userId);
  List<CartItem> findByUser_UserId(long userId);
}