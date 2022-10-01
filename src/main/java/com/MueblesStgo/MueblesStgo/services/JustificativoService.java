package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.JustificativoEntity;
import com.MueblesStgo.MueblesStgo.repositories.JustificativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class JustificativoService {
    @Autowired // Proporciona control de instancias
    JustificativoRepository justificativoRepository;

    /*
    El siguiente método retorna un arreglo el cual contiene TODOS justificativos de la base de datos
     */
    public ArrayList<JustificativoEntity> obtenerJustificativos(){
        return (ArrayList<JustificativoEntity>) justificativoRepository.findAll();
    }

    /*
    El siguiente método permite guardar un justificativos en la base de datos
     */
    public JustificativoEntity guardarJustificativo(JustificativoEntity justificativo){
        return justificativoRepository.save(justificativo);
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
    El siguiente método retorna un booleano dependiendo dependiendo de la existencia de una
    justificacion asociada a una fecha y rut determinados
     */
    public boolean estaJustificado(LocalDate fechaInasistencia, String rutEmpleado){
        ArrayList<JustificativoEntity> justificativoEntityArrayList = obtenerJustificativos();
        for (int i = 0; i < justificativoEntityArrayList.size(); i++){
            if (justificativoEntityArrayList.get(i).getRutEmpleado().equals(rutEmpleado) &&
            justificativoEntityArrayList.get(i).getFechaInasistencia().equals(fechaInasistencia)){
                return true;
            }
        }
        return false;
    }
}
