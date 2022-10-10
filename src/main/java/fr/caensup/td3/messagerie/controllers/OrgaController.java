package fr.caensup.td3.messagerie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;

@Controller
@RequestMapping({ "/orgas", "/orgas/" })
public class OrgaController {

	@Autowired
	private VueJS vue;

	@ModelAttribute("vue")
	public VueJS getVue() {
		return this.vue;
	}

	@GetMapping("")
	public String indexAction() {
		return "index";
	}
}
