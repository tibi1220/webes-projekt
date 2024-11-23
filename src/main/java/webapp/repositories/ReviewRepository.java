package webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webapp.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
}

