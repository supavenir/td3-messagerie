package fr.caensup.td3.messagerie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.caensup.td3.messagerie.repositories.OrgaRepository;
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
	private OrgaRepository orgaRepo;

	@ModelAttribute("vue")
	public VueJS getVue() {
		return this.vue;
	}

	@GetMapping("")
	public String indexAction() {
		vue.addData("toDelete");
		vue.addData("orga");
		vue.addData("organizations", orgaRepo.findAll());
		vue.addMethod("remove", Http.delete("'" + restURL + "'+orga.id",
				"this.toDelete=null;" + JsArray.remove("this.organizations", "orga")), "orga");
		vue.addMethod("confDelete", "this.toDelete=orga", "orga");
		vue.addMethod("newFormOrga", "this.orga={}");
		vue.addMethod("newOrgaSubmit", "if($('.ui.form').form('validate form')){" + Http.post(restURL, "this.orga",
				JsArray.add("this.organizations", "response.data") + ";this.orga=null;") + "}");
		vue.onMounted(
				"$('#form').form({'on': 'blur', 'inline': false, 'fields':{ 'name': {identifier: 'name','rules':['empty']}, 'aliases': 'empty', 'domain': 'empty'}});");
		vue.addMethod("editForm", "this.orga=orga", "orga");
		return "index";
	}
}
