package com.malyvoj3.csvwvalidator.persistance.repository;

import com.malyvoj3.csvwvalidator.persistance.domain.ResultEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends CrudRepository<ResultEntity, String> {
}
