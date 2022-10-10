package fr.caensup.td3.messagerie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.caensup.td3.messagerie.repositories.OrgaRepository;
import fr.caensup.td3.messagerie.services.UIOrgaService;
import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import io.github.jeemv.springboot.vuejs.utilities.JsArray;

@Controller
@RequestMapping({ "/orgas", "/orgas/" })
public class OrgaController {

	private static String restURL = "http://127.0.0.1:8080/rest/organizations/";

	@Autowired
	private VueJS vue;

	@Autowired
	private UIOrgaService orgaService;

	@Autowired
	private OrgaRepository orgaRepo;

	@ModelAttribute("vue")
	public VueJS getVue() {
		return this.vue;
	}

	@GetMapping("")
	public String indexAction() {
		// Ajout des méthodes à l'objet VueJS
		vue.addData("toDelete");
		vue.addData("orga");
		vue.addData("organizations", orgaRepo.findAll());
		// Ajout des méthodes à l'objet VueJS
		vue.addMethod("remove", Http.delete(orgaService.getURL(restURL, "orga.id"),
				"this.toDelete=null;" + JsArray.remove("this.organizations", "orga")), "orga");
		vue.addMethod("confDelete", "this.toDelete=orga", "orga");
		vue.addMethod("newFormOrga", "this.orga={};");
		vue.addMethod("newOrgaSubmit",
				orgaService.ifFormIsValid(Http.post(restURL, "this.orga",
						JsArray.add("this.organizations", "response.data")
								+ orgaService.toast("success", "Organisation ${this.orga.name} ajoutée.")
								+ ";this.orga=null;")));
		vue.addMethod("editForm", "this.orga={ ...orga};this.orga.original=orga;", "orga");
		vue.onUpdatedNextTick(orgaService.getFormValidation());
		vue.addMethod("updateOrgaSubmit",
				orgaService.ifFormIsValid(Http.put(orgaService.getURL(restURL, "this.orga.id"), (Object) "this.orga",
						"Object.assign(this.orga.original,response.data);"
								+ orgaService.toast("success", "Organisation ${this.orga.name} modifiée.")
								+ "this.orga=null;")));
		return "index";
	}
}
