package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.DescuentoEntity;
import com.MueblesStgo.MueblesStgo.repositories.DescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class DescuentoService {
    @Autowired // Proporciona control de instancias
    DescuentoRepository descuentoRepository;

    /*
    El siguiente método retorna un arreglo el cual contiene TODOS los descuentos de la base de datos
     */
    public ArrayList<DescuentoEntity> obtenerDescuento(){
        return (ArrayList<DescuentoEntity>) descuentoRepository.findAll();
    }

    /*
    El siguiente método permite guardar un descuento en la base de datos
     */
    public DescuentoEntity guardarDescuento(DescuentoEntity descuento){
        return descuentoRepository.save(descuento);
    }

    /*
    El siguiente método retorna una lista de dos objetos de flotantes correspondientes a
    dos constantes, cotización previsional y plan de salud respectivamente
     */
    public float[] obtenerCotizaciones(){
        ArrayList<DescuentoEntity> descuentoEntities = (ArrayList<DescuentoEntity>) descuentoRepository.findAll();
        float[] cotizaciones = new float[2];
        cotizaciones[0] = descuentoEntities.get(0).getCotizacionPrevisional();
        cotizaciones[1] = descuentoEntities.get(0).getCotizacionPlanSalud();
        return cotizaciones;
    }

    /*
    El siguiente método retorna las horas (LocalTime) no trabajadas de acuerdo con las horas trabajadas
    y las horas de trabajo por dia en la empresa
     */
    public LocalTime tiempoNoTrabajo(LocalTime tiempoTrabajo){
        ArrayList<DescuentoEntity> descuentoEntityArrayList = obtenerDescuento();
        LocalTime tiempoFaltante = descuentoEntityArrayList.get(0).getTiempoTrabajo().minusHours(tiempoTrabajo.getHour());
        tiempoFaltante = tiempoFaltante.minusMinutes(tiempoTrabajo.getMinute());
        tiempoFaltante = tiempoFaltante.minusSeconds(tiempoTrabajo.getSecond());
        return tiempoFaltante;
    }

    /*
    El siguiente método retorna un arreglo de dos flotantes que, de acuerdo con el trabajo
    faltante o no trabajado, corresponden al descuento a realizar sobre el sueldo y la
    posibilidad de apelar a justificativo o no
     */
    public ArrayList<Float> descuento(LocalTime tiempoTrabajoFaltante){
        ArrayList<DescuentoEntity> descuentoEntityArrayList = obtenerDescuento();
        ArrayList<Float> descuentoApelar = new ArrayList<>();
        float porcentajeMinimoDescuento = 0;
        float puedeApelar = 0; // 0 para no, 1 para si
        int aux;
        int i = 0;
        for(i = 0; i < descuentoEntityArrayList.size(); i++){
            aux = tiempoTrabajoFaltante.compareTo(descuentoEntityArrayList.get(i).getTiempoRetraso());
            if(aux < 0){ // Si tiempoTrabajoFaltante < tiempoRetraso entonces...
                if(i == 0){ // No hay descuento
                    descuentoApelar.add(porcentajeMinimoDescuento);
                    descuentoApelar.add(puedeApelar);
                    return descuentoApelar;
                }
                descuentoApelar.add(descuentoEntityArrayList.get(i - 1).getMontoDescuento());
                descuentoApelar.add(puedeApelar);
                return descuentoApelar;
            }
        }
        descuentoApelar.add(descuentoEntityArrayList.get(i - 1).getMontoDescuento()); // descuento maximo
        puedeApelar = puedeApelar + 1; // puede apelar
        descuentoApelar.add(puedeApelar);
        return descuentoApelar;
    }

    /*
    El siguiente método retorna el monto resultante tras aplicarle un descuento porcentual
     */
    public float aplicacionDescuentos(float sueldo, float porcentajeDescuento){
        float minimo = 0;
        float maximo = 100;
        if (porcentajeDescuento == minimo){
            return sueldo;
        }
        else if (porcentajeDescuento >= maximo){
            return 0;
        }
        return sueldo - (porcentajeDescuento * sueldo) / 100;
    }
}
