package fr.caensup.td3.messagerie.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import fr.caensup.td3.messagerie.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
  public Optional<User> findByEmail(String email);
}
