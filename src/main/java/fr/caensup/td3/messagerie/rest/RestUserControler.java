package fr.caensup.td3.messagerie.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.caensup.td3.messagerie.models.User;
import fr.caensup.td3.messagerie.repositories.UserRepository;

@RestController
@RequestMapping({"/users", "/users/"})
public class RestUserControler {
  @Autowired private UserRepository UserRepo;

  @GetMapping("")
  public Iterable<User> indexAction() {
    return UserRepo.findAll();
  }

  @GetMapping("{id}")
  public User userByIdAction(@PathVariable int id) {
    Optional<User> opt = UserRepo.findById(id);
    if (opt.isPresent()) {
      return opt.get();
    }
    return null;
  }

  @PostMapping("")
  public User addAction(@RequestBody User us) {
    UserRepo.save(us);
    return us;
  }

  @PutMapping("{id}")
  public User updateAction(@PathVariable int id, @RequestBody User us) {
    Optional<User> opt = UserRepo.findById(id);
    if (opt.isPresent()) {
      User usloaded = opt.get();
      usloaded.setEmail(us.getEmail());
      usloaded.setFirstname(us.getFirstname());
      usloaded.setLastname(us.getLastname());
      usloaded.setPassword(us.getPassword());
      usloaded.setSuspended(us.isSuspended());
      UserRepo.save(usloaded);
      return usloaded;
    }
    return null;
  }
}
