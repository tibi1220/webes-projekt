package webapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "\"user\"")
public class User {
  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long userId;

  @NotBlank
  @Column(unique = true)
  private String username;

  @Email
  @NotBlank
  @Column(unique = true)
  private String email;

  @NotBlank
  private String password;

  // Getters and Setters
  public long getUserId() {
    return userId;
  }
  public void setUserId(long id) {
    this.userId = id;
  }

  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}