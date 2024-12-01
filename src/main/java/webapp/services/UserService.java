package webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.entities.User;
import webapp.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  // Check if username or email already exists
  public boolean usernameExists(String username) {
    return userRepository.existsByUsername(username);
  }
  public boolean emailExists(String email) {
    return userRepository.existsByEmail(email);
  }

  // For registering a new user
  public void registerUser(User user) {
    userRepository.save(user);
  }
}
