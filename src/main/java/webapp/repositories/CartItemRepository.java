package webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webapp.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
}