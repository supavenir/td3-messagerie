package fr.caensup.td3.messagerie.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.caensup.td3.messagerie.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {}
