package fr.caensup.td3.messagerie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fr.caensup.td3.messagerie.ui.UIMessage;

@Controller
public class MainController extends AbstractController {

  @GetMapping("/login")
  public String loginAction(@RequestParam(required = false) String error,
      @RequestParam(required = false) String logout) {
    if (error != null) {
      vue.addData("message", new UIMessage("Erreur d'authenficiation",
          "Identifiants de connexion incorrects !", "error", "warning circle"));
    }
    if (logout != null) {
      vue.addData("message", new UIMessage("Déconnexion",
          "Vous avez été correctement déconnecté de l'application.", "success", "info circle"));
    }
    return "/login";
  }
}
