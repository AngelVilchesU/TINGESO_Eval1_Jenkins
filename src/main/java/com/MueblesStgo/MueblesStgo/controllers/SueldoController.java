package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.SueldoEntity;
import com.MueblesStgo.MueblesStgo.services.SueldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@RestController
@RequestMapping("/sueldo")
public class SueldoController {
    @Autowired
    SueldoService sueldoService;

    /*
        El siguiente método permite redireccionar a la vista (listarSueldos.html) mediante el
        uso de ModelAndView quien brinda un servicio que empaqueta datos del modelo y vista
        juntos en paralelo con su retorno
         */
    @RequestMapping("/listarSueldos")
    public ModelAndView listarSueldos () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("listarSueldos");
        ArrayList<SueldoEntity> sueldoEntityArrayList = sueldoService.obtenerSueldo();
        modelAndView.addObject("sueldos", sueldoEntityArrayList);
        return modelAndView;
    }

    /*
        El siguiente método permite redireccionar a la vista (calcularPlanilla.html) mediante el
        uso de ModelAndView quien brinda un servicio que empaqueta datos del modelo y vista
        juntos en paralelo con su retorno
         */
    @RequestMapping("/calcularPlanilla")
    public ModelAndView calcularPlanillaS () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("calcularPlanilla");
        return modelAndView;
    }

    /*
    El siguiente método retorna los datos de la presente entidad desde la vista de sueldo,
    es decir, "/sueldo" lo cual permite visualizar el contenido de la base de datos en dicha
    dirección
     */
    @GetMapping()
    public ArrayList<SueldoEntity> obtenerSueldo(){
        return sueldoService.obtenerSueldo();
    }

    /*
    El siguiente método permite guardar en la base de datos un objeto tipo sueldo a la
    base de datos
     */
    @PostMapping()
    public SueldoEntity guardarSueldo(@RequestBody SueldoEntity sueldo){
        return this.sueldoService.guardarSueldo(sueldo);
    }

    @PostMapping("/calcularPlanilla") // /query?dia=14&mes=9&anio=2022
    public RedirectView calculoPlanillas(@RequestParam("ruta") String ruta, @RequestParam("nombreArchivo") String nombreArchivo){
        sueldoService.calculoPlanillas(ruta, nombreArchivo);
        return new RedirectView("/sueldo/calcularPlanilla");
    }
}
