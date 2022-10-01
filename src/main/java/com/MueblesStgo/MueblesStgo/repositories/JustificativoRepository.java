package com.MueblesStgo.MueblesStgo.repositories;

import com.MueblesStgo.MueblesStgo.entities.JustificativoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JustificativoRepository extends CrudRepository<JustificativoEntity, Long> {

}
