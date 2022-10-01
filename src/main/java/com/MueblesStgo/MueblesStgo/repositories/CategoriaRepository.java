package com.MueblesStgo.MueblesStgo.repositories;

import com.MueblesStgo.MueblesStgo.entities.CategoriaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRepository extends CrudRepository<CategoriaEntity, Long> {
}
