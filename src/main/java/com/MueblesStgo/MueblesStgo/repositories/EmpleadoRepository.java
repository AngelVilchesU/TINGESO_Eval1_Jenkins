package com.MueblesStgo.MueblesStgo.repositories;

import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpleadoRepository extends CrudRepository<EmpleadoEntity, Long> {

    /*
    El siguiente método permite retornar el usuario desde la base de datos
    con el rut que sea ingresado
     */
    public abstract EmpleadoEntity findByRut(String rut);
}
