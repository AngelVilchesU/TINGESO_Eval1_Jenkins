package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.BonificacionEntity;
import com.MueblesStgo.MueblesStgo.services.BonificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/bonificacion")
public class BonificacionController {
    @Autowired
    BonificacionService bonificacionService;

    /*
    El siguiente método retorna los datos de la presente entidad desde la vista de bonificacion,
    es decir, "/bonificacion" lo cual permite visualizar el contenido de la base de datos en dicha
    dirección
     */
    @GetMapping()
    public ArrayList<BonificacionEntity> obtenerBonificacion(){
        return bonificacionService.obtenerBonificacion();
    }

    /*
    El siguiente método permite guardar en la base de datos un objeto tipo bonificacion a la
    base de datos
     */
    @PostMapping()
    public BonificacionEntity guardarBonificacion(@RequestBody BonificacionEntity bonificacion){
        return this.bonificacionService.guardarBonificacion(bonificacion);
    }
}
