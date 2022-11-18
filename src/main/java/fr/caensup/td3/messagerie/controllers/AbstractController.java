package fr.caensup.td3.messagerie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import fr.caensup.td3.messagerie.pojo.ActiveUser;
import io.github.jeemv.springboot.vuejs.VueJS;

public abstract class AbstractController {

  public static final String VERSION = "0.0.2";

  @Autowired
  protected VueJS vue;


  @ModelAttribute("user")
  public ActiveUser getActiveUsername() {
    return new ActiveUser(SecurityContextHolder.getContext().getAuthentication());
  }

  @ModelAttribute("vue")
  public VueJS getVue() {
    return this.vue;
  }

  @ModelAttribute("version")
  public String getVersion() {
    return AbstractController.VERSION;
  }

}
