package webapp.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "\"order\"")
public class Order {
  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long orderId;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "userId")
  private User user;

  @CreationTimestamp
  private LocalDateTime orderDate;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems;

  // Getters and Setters
  public long getOrderId() {
    return orderId;
  }
  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }

  public LocalDateTime getOrderDate() {
    return orderDate;
  }
  public void setOrderDate(LocalDateTime orderDate) {
    this.orderDate = orderDate;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }
  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }
}
