package com.MueblesStgo.MueblesStgo.repositories;

import com.MueblesStgo.MueblesStgo.entities.SueldoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SueldoRepository extends CrudRepository<SueldoEntity, Long> {
}
