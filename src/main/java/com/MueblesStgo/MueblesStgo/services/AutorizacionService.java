package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.AutorizacionEntity;
import com.MueblesStgo.MueblesStgo.repositories.AutorizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class AutorizacionService {
    @Autowired // Proporciona control de instancias
    AutorizacionRepository autorizacionRepository;

    /*
    El siguiente método retorna un arreglo el cual contiene TODAS las autorizaciones de la base de datos
     */
    public ArrayList<AutorizacionEntity> obtenerAutorizaciones(){
        return (ArrayList<AutorizacionEntity>) autorizacionRepository.findAll();
    }

    /*
    El siguiente método permite guardar una autorización en la base de datos
     */
    public AutorizacionEntity guardarAutorizacion (AutorizacionEntity autorizacion){
        return autorizacionRepository.save(autorizacion);
    }

    /*
    El siguiente método retorna un dato tipo LocalDate con un formato YYYY-MM-DD a partir
    de un string de formato de entrada "YYYY-MM-DD"
     */
    public LocalDate fechaFormato(String fecha){
        String[] parte = fecha.split("-");
        int dia = Integer.parseInt(parte[2]);
        int mes = Integer.parseInt(parte[1]);
        int anio = Integer.parseInt(parte[0]);
        return LocalDate.of(anio, mes, dia);
    }

    /*
    El siguiente método retorna un valor booleano dependiendo de la existencia de
    una autorización asociada a una fecha y rut determinados
     */
    public boolean tieneAutorizacion(LocalDate fechaHorasExtra, String rutEmpleado){
        ArrayList<AutorizacionEntity> autorizacionEntityArrayList = obtenerAutorizaciones();
        for (int i = 0; i < autorizacionEntityArrayList.size(); i++){
            if(autorizacionEntityArrayList.get(i).getFechaHoraExtra().equals(fechaHorasExtra) &&
            autorizacionEntityArrayList.get(i).getRutEmpleado().equals(rutEmpleado)){
                return true;
            }
        }
        return false;
    }

    /*
    El siguiente método retorna las horas extras autorizadas asociadas a una fecha y rut
    determinados
     */
    public float horasExtra(LocalDate fechaHorasExtra, String rutEmpleado){
        ArrayList<AutorizacionEntity> autorizacionEntityArrayList = obtenerAutorizaciones();
        for (int i = 0; i < autorizacionEntityArrayList.size(); i++){
            if(autorizacionEntityArrayList.get(i).getFechaHoraExtra().equals(fechaHorasExtra) &&
                    autorizacionEntityArrayList.get(i).getRutEmpleado().equals(rutEmpleado)){
                return autorizacionEntityArrayList.get(i).getHorasExtra();
            }
        }
        return 0;
    }
}
