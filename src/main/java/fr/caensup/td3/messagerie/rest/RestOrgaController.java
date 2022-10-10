package fr.caensup.td3.messagerie.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.caensup.td3.messagerie.exceptions.OrgaNotFoundException;
import fr.caensup.td3.messagerie.models.Organization;
import fr.caensup.td3.messagerie.repositories.OrgaRepository;

@RestController
@RequestMapping("/rest/organizations")
public class RestOrgaController {
	@Autowired
	private OrgaRepository orgaRepo;

	@GetMapping("")
	public Iterable<Organization> indexAction() {
		return orgaRepo.findAll();
	}

	@GetMapping("/{id}")
	public Organization oneAction(@PathVariable int id) {
		Optional<Organization> opt = orgaRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new OrgaNotFoundException(id);
	}

	@PostMapping("")
	public Organization addAction(@RequestBody Organization orga) {
		orgaRepo.save(orga);
		return orga;
	}

	@DeleteMapping("{id}")
	public RestMessage deleteAction(@PathVariable int id) {
		Optional<Organization> opt = orgaRepo.findById(id);
		if (opt.isPresent()) {
			orgaRepo.deleteById(id);
			return new RestMessage("200", "Organisation supprimée : " + id);
		}
		throw new OrgaNotFoundException(id);
	}

	@PutMapping("{id}")
	public Organization updateAction(@RequestBody Organization orga, @PathVariable int id) {
		return orgaRepo.findById(id).map((loadedOrga) -> {
			loadedOrga.setName(orga.getName());
			loadedOrga.setAliases(orga.getAliases());
			loadedOrga.setDomain(orga.getDomain());
			orgaRepo.save(loadedOrga);
			return loadedOrga;
		}).orElseThrow(() -> new OrgaNotFoundException(id));
	}
}
