package de.ckonv.reactivedemo.reactivemariadb.persistence;

import de.ckonv.reactivedemo.reactivemariadb.domain.model.BusinessObject2;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BusinessObject2Repository
    extends ReactiveCrudRepository<BusinessObject2, Integer> {}
