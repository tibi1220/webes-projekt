package webapp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"order_item\"")
public class OrderItem {
  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long orderItemId;

  @ManyToOne
  @JoinColumn(name = "order_id", referencedColumnName = "orderId")
  private Order order;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "productId")
  private Product product;

  @NotNull
  private int quantity;

  // Getters and Setters
  public long getOrderItemId() {
    return orderItemId;
  }
  public void setOrderItemId(long orderItemId) {
    this.orderItemId = orderItemId;
  }

  public Order getOrder() {
    return order;
  }
  public void setOrder(Order order) {
    this.order = order;
  }

  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
