package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
import com.MueblesStgo.MueblesStgo.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmpleadoService {
    @Autowired // Proporciona control de instancias
    EmpleadoRepository empleadoRepository;

    /*
    El siguiente método retorna un arreglo el cual contiene a TODOS los empleados de la base de datos
     */
    public ArrayList<EmpleadoEntity> obtenerEmpleados(){
        return (ArrayList<EmpleadoEntity>) empleadoRepository.findAll();
    }

    /*
    El siguiente método permite guardar un empleado en la base de datos
     */
    public EmpleadoEntity guardarEmpleado(EmpleadoEntity empleado){
        return empleadoRepository.save(empleado);
    }

    /*
    El siguiente método permite retornar un empleado de acuerdo con su rut
     */
    public EmpleadoEntity obtenerPorRut(String rut){
        return empleadoRepository.findByRut(rut);
    }
}
