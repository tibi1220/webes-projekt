package webapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webapp.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
  List<Review> findByProduct_ProductId(long productId);
  List<Review> findByUser_UserId(long userId);
}

