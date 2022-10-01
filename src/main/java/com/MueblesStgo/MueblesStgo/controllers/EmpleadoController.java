package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
import com.MueblesStgo.MueblesStgo.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    /*
    El siguiente método retorna los datos de la presente entidad desde la vista de empleado,
    es decir, "/empleado" lo cual permite visualizar el contenido de la base de datos en dicha
    dirección
     */
    @GetMapping()
    public ArrayList<EmpleadoEntity> obtenerEmpleados(){
        return empleadoService.obtenerEmpleados();
    }

    /*
    El siguiente método permite guardar en la base de datos un objeto tipo empleado a la
    base de datos
     */
    @PostMapping()
    public EmpleadoEntity guardarEmpleado(@RequestBody EmpleadoEntity empleado){
        return this.empleadoService.guardarEmpleado(empleado);
    }
}
