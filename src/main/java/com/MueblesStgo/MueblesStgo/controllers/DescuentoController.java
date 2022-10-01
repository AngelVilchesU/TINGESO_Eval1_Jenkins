package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.DescuentoEntity;
import com.MueblesStgo.MueblesStgo.services.DescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/descuento")
public class DescuentoController {
    @Autowired
    DescuentoService descuentoService;

    /*
    El siguiente método retorna los datos de la presente entidad desde la vista de descuento,
    es decir, "/descuento" lo cual permite visualizar el contenido de la base de datos en dicha
    dirección
     */
    @GetMapping()
    public ArrayList<DescuentoEntity> obtenerDescuento(){
        return descuentoService.obtenerDescuento();
    }

    /*
    El siguiente método permite guardar en la base de datos un objeto tipo descuento a la
    base de datos
     */
    @PostMapping()
    public DescuentoEntity guardarDescuento(@RequestBody DescuentoEntity descuento){
        return this.descuentoService.guardarDescuento(descuento);
    }
}
