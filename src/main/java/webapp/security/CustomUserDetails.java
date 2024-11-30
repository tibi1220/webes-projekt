package webapp.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
  private long userId;

  public CustomUserDetails(webapp.entities.User user) {
    super(user.getUsername(), user.getPassword(), new ArrayList<>());
    this.userId = user.getUserId();
  }

  public long getUserId() {
    return userId;
  }
}
