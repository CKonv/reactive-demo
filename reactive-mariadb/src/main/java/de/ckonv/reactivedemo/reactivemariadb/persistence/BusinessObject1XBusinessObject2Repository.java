package de.ckonv.reactivedemo.reactivemariadb.persistence;

import de.ckonv.reactivedemo.reactivemariadb.domain.model.BusinessObject1XBusinessObject2;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BusinessObject1XBusinessObject2Repository
    extends ReactiveCrudRepository<BusinessObject1XBusinessObject2, Integer> {}
