package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.ArchivoEntity;
import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
import com.MueblesStgo.MueblesStgo.repositories.ArchivoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class ArchivoService {
    @Autowired // Proporciona control de instancias
    ArchivoRepository archivoRepository;
    @Autowired // Proporciona control de instancias
    EmpleadoService empleadoService;
    private String carpetaDestino="Marcas//"; // constante nombre de la carpeta a crear (si no lo está)
    private final Logger carga = LoggerFactory.getLogger(ArchivoService.class); // registrador de datos de acuerdo a una instancia


    /*
    El siguiente método retorna un arreglo el cual contiene a TODAS las marcas de reloj, es decir,
    objetos desde la entidad ArchivoEntity
     */
    public ArrayList<ArchivoEntity> obtenerMarcas(){
        return (ArrayList<ArchivoEntity>) archivoRepository.findAll();
    }
    /*
    El siguiente método permite guardar/registrar una marca de reloj en la base de datos.
     */
    public ArchivoEntity guardarMarca(ArchivoEntity marca){
        return archivoRepository.save(marca);
    }

    /*
    El siguiente método dado un archivo/file extrae su información ubicandole en una dirección de
    carpeta determinada luego leer su contenido. En este sentido, es importante destacar el uso
    de byte[] como un arreglo de bytes
     */
    public String cargarArchivo(MultipartFile archivo){
        if (archivo.isEmpty()){ // Si el archivo está vacio
            return "El archivo no se ha subido exitosamente";
        }
        else { // Caso contrario
            crearDirectorio();
            try {
                byte[] arrayByte = archivo.getBytes(); // se extraen como bytes el contenido del archivo
                Path ruta = Paths.get(carpetaDestino + archivo.getOriginalFilename()); // ruta destino
                Files.write(ruta, arrayByte); // se escribe/carga el archivo en la ruta
                carga.info("Archivo subido"); // para mostrar un mensaje de confirmación
            }
            catch (IOException error){
                error.printStackTrace();
            }
            leerArchivo(carpetaDestino + archivo.getOriginalFilename()); // llamado a método
            return "El archivo se ha subido exitosamente";
        }
    }

    /*
    El siguiente método permite leer y almacenar la información leida con formato (esclarecido al
    final del presente escrito) en la base de datos.
    Formato: YYYY/MM/DD;HH:MM;rut con punto y guión
    Ej: 2022/09/10;20:03;12.345.678-9
     */
    public String leerArchivo(String ruta){
        File archivo = new File(ruta);
        try (Scanner escaner = new Scanner(archivo)){
            while (escaner.hasNextLine()){ // Mientras el archivo posea una siguiente linea (no se lea completamente)
                String linea = escaner.nextLine(); // Se extrae la linea "actual"
                String[] parte = linea.split(";"); // el string se divide en partes a partir del caracter ";"
                String fechaTmp = parte[0].replace("/", "-"); // La primera de estas partes contempla la fecha
                LocalDate fecha = LocalDate.parse(fechaTmp); // El string fecha es convertido a LocalDate
                String horaTmp = parte[1]; // La segunda parte contempla la hora
                LocalTime hora = LocalTime.parse(horaTmp); // El string hora es convertido a LocalTime
                String rut = parte[2]; // La tercera parte contempla el rut
                EmpleadoEntity empleado = new EmpleadoEntity(); // Se instancia una entidad empleado
                empleado = empleadoService.obtenerPorRut(rut); // Si el empleado existe (caso normal) se instancia para relacionar a la marca
                guardarMarca(new ArchivoEntity(fecha, hora, rut, empleado)); // Se guarda la marca en la base de datos
            }
        }
        catch (FileNotFoundException error){
            error.printStackTrace();
        }
        return "El archivo se ha leido existosamente";
    }

    public boolean crearDirectorio(){
        File directorio = new File(carpetaDestino);
        if(!directorio.exists()){
            directorio.mkdirs();
            return true;
        }
        else {
            return false;
        }
    }
}
