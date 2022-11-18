package fr.caensup.td3.messagerie.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import fr.caensup.td3.messagerie.models.User;
import fr.caensup.td3.messagerie.repositories.UserRepository;


public class DbUserLoginService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder; // (1)

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> opt = userRepository.findByEmail(email);
    if (opt.isPresent()) {
      return opt.get();
    }
    throw new UsernameNotFoundException(email + " n'existe pas !");
  }

  public void encodePassword(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
  }

}
