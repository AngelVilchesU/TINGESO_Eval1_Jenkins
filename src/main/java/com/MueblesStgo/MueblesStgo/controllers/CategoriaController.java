package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.CategoriaEntity;
import com.MueblesStgo.MueblesStgo.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    /*
    El siguiente método retorna los datos de la presente entidad desde la vista de categoria,
    es decir, "/categoria" lo cual permite visualizar el contenido de la base de datos en dicha
    dirección
     */
    @GetMapping()
    public ArrayList<CategoriaEntity> obtenerCategoria(){
        return categoriaService.obtenerCategoria();
    }

    /*
    El siguiente método permite guardar en la base de datos un objeto tipo categoria a la
    base de datos
     */
    @PostMapping()
    public CategoriaEntity guardarCategoria(@RequestBody CategoriaEntity categoria){
        return this.categoriaService.guardarCategoria(categoria);
    }
}
