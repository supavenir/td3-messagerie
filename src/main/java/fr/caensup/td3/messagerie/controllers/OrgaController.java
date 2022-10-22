package fr.caensup.td3.messagerie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
@RequestMapping({"/", ""})
public class OrgaController {

  private static String restURL;

  @Autowired private Environment env;

  @Autowired private VueJS vue;

  @Autowired private UIOrgaService orgaService;

  @Autowired private OrgaRepository orgaRepo;

  @ModelAttribute("vue")
  public VueJS getVue() {
    return this.vue;
  }

	@GetMapping("")
	public String indexAction() {
		restURL = env.getProperty("rest.url") + "organizations/";
		// Ajout des data à l'objet VueJS
		vue.addData("toDelete");
		vue.addData("orga");
		vue.addData("groups", "[]");
		vue.addData("organizations", orgaRepo.findAll());
		// Ajout des méthodes à l'objet VueJS
		vue.addMethod("remove", Http.delete(orgaService.getURL(restURL, "orga.id"),
				"this.toDelete=null;" + JsArray.remove("this.organizations", "orga")), "orga");
		vue.addMethod("confDelete", vue.set("toDelete", "orga"), "orga");
		vue.addMethod("newFormOrga", vue.set("orga", "{}"));
		vue.addMethod("newOrgaSubmit",
				orgaService.ifFormIsValid(Http.post(restURL, "this.orga",
						Http.responseToArray("this.organizations")
								+ orgaService.toast("success", "Organisation ${this.orga.name} ajoutée.")
								+ vue.set("orga", "null"))));
		vue.addMethod("editForm", vue.cloneOriginalData("pOrga", "orga"), "pOrga");
		vue.onUpdatedNextTick(orgaService.getFormValidation());
		vue.addMethod("updateOrgaSubmit",
				orgaService.ifFormIsValid(Http.put(orgaService.getURL(restURL, "this.orga.id"), (Object) "this.orga",
						vue.assignOriginalWithHttp("orga")
								+ orgaService.toast("success", "Organisation ${this.orga.name} modifiée.")
								+ vue.set("orga", "null"))));
		vue.addMethod("addOrUpdate", "if(this.orga.id){this.updateOrgaSubmit();}else{this.newOrgaSubmit();}");
		vue.addMethod("popup",
				";" + Http.get(orgaService.getURL(restURL, "orga.id") + "+'/groups'", "this.groups=response.data;"),
				"orga");
		vue.addDirective("focus").onInserted("el.focus()");
		vue.addDirective("popup").onInserted("$(el).popup({popup: $('.ui.popup'), on: 'click'});");
		return "index";
	}
}
