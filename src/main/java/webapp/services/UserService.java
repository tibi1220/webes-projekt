package webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.entities.User;
import webapp.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public void registerUser(User user) {
    userRepository.save(user);  // Save the new user to the database
  }
}
