package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.BonificacionEntity;
import com.MueblesStgo.MueblesStgo.repositories.BonificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BonificacionService {
    @Autowired // Proporciona control de instancias
    BonificacionRepository bonificacionRepository;

    /*
    El siguiente método retorna un arreglo el cual contiene TODAS las bonificaciones de la base de datos
     */
    public ArrayList<BonificacionEntity> obtenerBonificacion(){
        return (ArrayList<BonificacionEntity>) bonificacionRepository.findAll();
    }

    /*
    El siguiente método permite guardar una bonificación en la base de datos
     */
    public BonificacionEntity guardarBonificacion(BonificacionEntity bonificacion){
        return bonificacionRepository.save(bonificacion);
    }

    /*
    El siguiente método retorna el monto adicional asociado al aumento porcentual de un
    sueldo ingresado
     */
    public float sueldoBonificacionPorcentual(float sueldo, float bPorcentual){
        float bPorcentajeDecimal = (bPorcentual + 100) / 100; // Conversión de porcentaje a decimal
        return sueldo * bPorcentajeDecimal - sueldo;
    }

    /*
    El siguiente método retorna el valor porcentual que figura como bonificación de acuerdo a los
    años de servicio en la empresa
     */
    public float bonificacionAniosServicio(float aniosServicio){
        int i;
        ArrayList<BonificacionEntity> bonificacionEntityArrayList = obtenerBonificacion();
        for (i = 0; i < bonificacionEntityArrayList.size(); i++){
            if(aniosServicio < bonificacionEntityArrayList.get(i).getAniosServicio()){
                if (i == 0){
                    return 0;
                }
                else {
                    return bonificacionEntityArrayList.get(i - 1).getBono();
                }
            }
        }
        return bonificacionEntityArrayList.get(i - 1).getBono();
    }
}
