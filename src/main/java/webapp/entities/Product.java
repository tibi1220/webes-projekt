package webapp.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"product\"")
public class Product {
  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long productId;

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  @NotNull
  @DecimalMin(value = "0")
  @DecimalMax(value = "10000")
  private BigDecimal price;

  // Getters and Setters
  public long getProductId() {
    return productId;
  }
  public void setProductId(long id) {
    this.productId = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
