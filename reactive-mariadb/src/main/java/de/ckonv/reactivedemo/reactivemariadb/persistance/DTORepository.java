package de.ckonv.reactivedemo.reactivemariadb.persistance;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DTORepository extends ReactiveCrudRepository<DTOEntity, Integer> {}
