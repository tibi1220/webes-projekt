package webapp.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "\"review\"")
public class Review {
  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long reviewId;

  @NotNull
  @DecimalMin(value = "1")
  @DecimalMax(value = "5")
  private int rating;

  private String title;

  private String content;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "productId")
  private Product product;

  // Getters and Setters
  public long getId() {
    return reviewId;
  }
  public void setId(long id) {
    this.reviewId = id;
  }

  public int getRating() {
    return rating;
  }
  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }

  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }
}