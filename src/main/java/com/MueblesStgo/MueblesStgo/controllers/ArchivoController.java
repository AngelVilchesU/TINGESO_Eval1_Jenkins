package com.MueblesStgo.MueblesStgo.controllers;

import com.MueblesStgo.MueblesStgo.entities.ArchivoEntity;
import com.MueblesStgo.MueblesStgo.services.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@RestController
@RequestMapping("/archivo")
public class ArchivoController {
    @Autowired
    private ArchivoService archivoService;

    /*
    El siguiente método permite redireccionar a la vista (cargarArchivo.html) mediante el
    uso de ModelAndView quien brinda un servicio que empaqueta datos del modelo y vista
    juntos en paralelo con su retorno
     */
    @RequestMapping("/cargarArchivo")
    public ModelAndView cargarArc() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cargarArchivo");
        return modelAndView;
    }

    /*
    El siguiente método retorna los datos de la presente entidad desde la vista del archivo,
    es decir, "/archivo" lo cual permite visualizar el contenido de la base de datos en dicha
    dirección
     */
    @GetMapping()
    public ArrayList<ArchivoEntity> obtenerData(){
        return archivoService.obtenerMarcas();
    }

    /*
    El siguiente método permite guardar un archivo de texto (.txt) desde la dirección
    "/cargarArchivo" y la vista correspondiente (cargarArchivo.html) haciendo uso de
    RedirectView quien permite redireccionar a la vista plasmada en la función. En este
    sentido, es importante destacar la participación de @RequestParam y MultipartFile, el
    primero de estos pertenece al controlador de servicios REST y brinda la posibilidad de
    extraer parámetros de consulta, en este caso un archivo. Por su parte, MultipartFile
    responde al almacenamiento temporal del contenido de un archivo recibido a través de una
    solicitud
     */
    @PostMapping("/cargarArchivo")
    public RedirectView guardarArchivo(@RequestParam ("DATA")MultipartFile archivo){
        archivoService.cargarArchivo(archivo);
        return new RedirectView("/archivo/cargarArchivo");
    }

}
