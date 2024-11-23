package webapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "\"cart_item\"")
public class CartItem {
  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long cartItemId;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private int quantity;

  // Getters and Setters
  public long getCartItemId() {
    return cartItemId;
  }
  public void setCartItemId(long cartItemId) {
    this.cartItemId = cartItemId;
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
