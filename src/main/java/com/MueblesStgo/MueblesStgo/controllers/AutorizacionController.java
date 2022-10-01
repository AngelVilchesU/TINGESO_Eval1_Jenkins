package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.AutorizacionEntity;
import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
import com.MueblesStgo.MueblesStgo.services.AutorizacionService;
import com.MueblesStgo.MueblesStgo.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/autorizacion")
public class AutorizacionController {
    @Autowired
    AutorizacionService autorizacionService;

    @Autowired
    EmpleadoService empleadoService;

    /*
    El siguiente método permite redireccionar a la vista (ingresarAutorizacion.html) mediante el
    uso de ModelAndView quien brinda un servicio que empaqueta datos del modelo y vista
    juntos en paralelo con su retorno
    */
    @RequestMapping("/ingresarAutorizacion")
    public ModelAndView ingresarAut () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ingresarAutorizacion");
        return modelAndView;
    }

    /*
    El siguiente método retorna los datos de la presente entidad desde la vista del justificativo,
    es decir, "/justificativo" lo cual permite visualizar el contenido de la base de datos en dicha
    dirección
     */
    @GetMapping()
    public ArrayList<AutorizacionEntity> obtenerAutorizaciones(){
        return autorizacionService.obtenerAutorizaciones();
    }

    /*
        El siguiente método permite guardar una autorización desde la dirección
        "/ingresarAutorizacion" y la vista correspondiente (ingresarAutorizacion.html) haciendo uso de
        RedirectView quien permite redireccionar a la vista plasmada en la función. En este
        sentido, es importante destacar la participación de @RequestParam quien pertenece al controlador
        de servicios REST y brinda la posibilidad de extraer parámetros de consulta, en este caso dos
        Strings
         */
    @PostMapping("/ingresarAutorizacion")
    public RedirectView guardarAutorizacion(@RequestParam(name="rut") String r, @RequestParam(name="fecha") String f, @RequestParam(name="hora") float h){
        AutorizacionEntity aut = new AutorizacionEntity();
        aut.setRutEmpleado(r);
        LocalDate fechaFormato = autorizacionService.fechaFormato(f);
        aut.setFechaHoraExtra(fechaFormato);
        EmpleadoEntity empleado = empleadoService.obtenerPorRut(r);
        aut.setEmpleado(empleado);
        aut.setHorasExtra(h);
        autorizacionService.guardarAutorizacion(aut);
        return new RedirectView("/autorizacion/ingresarAutorizacion");

    }
}
