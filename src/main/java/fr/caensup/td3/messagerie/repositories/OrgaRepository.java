package fr.caensup.td3.messagerie.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.caensup.td3.messagerie.models.Organization;

public interface OrgaRepository extends CrudRepository<Organization, Integer> {}
