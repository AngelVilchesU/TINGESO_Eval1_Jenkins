package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
import com.MueblesStgo.MueblesStgo.entities.JustificativoEntity;
import com.MueblesStgo.MueblesStgo.services.EmpleadoService;
import com.MueblesStgo.MueblesStgo.services.JustificativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/justificativo")
public class JustificativoController {
    @Autowired
    JustificativoService justificativoService;

    @Autowired
    EmpleadoService empleadoService;

    /*
        El siguiente método permite redireccionar a la vista (ingresarJustificativo.html) mediante el
        uso de ModelAndView quien brinda un servicio que empaqueta datos del modelo y vista
        juntos en paralelo con su retorno
         */
    @RequestMapping("/ingresarJustificativo")
    public ModelAndView ingresarJust () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ingresarJustificativo");
        return modelAndView;
    }

    /*
    El siguiente método retorna los datos de la presente entidad desde la vista del justificativo,
    es decir, "/justificativo" lo cual permite visualizar el contenido de la base de datos en dicha
    dirección
     */
    @GetMapping()
    public ArrayList<JustificativoEntity> obtenerJustificativos(){
        return justificativoService.obtenerJustificativos();
    }

    /*
    El siguiente método permite guardar un justificativo desde la dirección
    "/ingresarJustificativo" y la vista correspondiente (ingresarJustificativo.html) haciendo uso de
    RedirectView quien permite redireccionar a la vista plasmada en la función. En este
    sentido, es importante destacar la participación de @RequestParam quien pertenece al controlador
    de servicios REST y brinda la posibilidad de extraer parámetros de consulta, en este caso dos
    Strings
     */
    @PostMapping("/ingresarJustificativo")
    public RedirectView guardarJustificativo(@RequestParam(name="rut") String r, @RequestParam(name="fecha") String f){
        JustificativoEntity just = new JustificativoEntity();
        just.setRutEmpleado(r);
        LocalDate fechaFormato = justificativoService.fechaFormato(f);
        just.setFechaInasistencia(fechaFormato);
        EmpleadoEntity empleado = empleadoService.obtenerPorRut(r);
        just.setEmpleado(empleado);
        justificativoService.guardarJustificativo(just);
        return new RedirectView("/justificativo/ingresarJustificativo");
    }


}
