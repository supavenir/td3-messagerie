package fr.caensup.td3.messagerie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController extends AbstractController {

  @GetMapping("/login")
  public String loginAction(@RequestParam(required = false) String error) {
    vue.addData("error", error != null);
    return "/login";
  }
}
