package fr.caensup.td3.messagerie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

import fr.caensup.td3.messagerie.models.Group;
import fr.caensup.td3.messagerie.models.Organization;
import fr.caensup.td3.messagerie.models.User;
import fr.caensup.td3.messagerie.repositories.OrgaRepository;

@RestController
public class InitController {

  @Autowired private OrgaRepository orgaRepo;

  @GetMapping("init/{cOrga}/{cGroup}/{cUser}")
  public @ResponseBody String initAction(
      @PathVariable int cOrga, @PathVariable int cGroup, @PathVariable int cUser) {
    for (int o = 0; o < cOrga; o++) {
      Organization orga = new Organization();
      Faker fake = new Faker();
      orga.setName(fake.animal().name());
      orga.setDomain(orga.getName() + ".fr");
      orga.setAliases(orga.getName() + ".com");
      for (int g = 0; g < cGroup; g++) {
        Group gr = new Group();
        gr.setName(fake.ancient().hero());
        gr.setEmail("@" + gr.getName() + ".fr");
        gr.setAliases(gr.getName() + ".com");
        orga.addGroup(gr);
        for (int u = 0; u < cUser; u++) {
          User us =
              new User(
                  fake.name().firstName(),
                  fake.name().lastName(),
                  fake.internet().emailAddress(),
                  "0000");
          orga.addUser(us);
          gr.addUSer(us);
        }
      }
      orgaRepo.save(orga);
    }
    return "init ok";
  }
}
