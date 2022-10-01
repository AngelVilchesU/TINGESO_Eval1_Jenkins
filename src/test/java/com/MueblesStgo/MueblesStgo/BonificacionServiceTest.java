package com.MueblesStgo.MueblesStgo;
import com.MueblesStgo.MueblesStgo.entities.BonificacionEntity;
import com.MueblesStgo.MueblesStgo.repositories.BonificacionRepository;
import com.MueblesStgo.MueblesStgo.services.BonificacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class BonificacionServiceTest {
    @Mock
    private BonificacionRepository bonificacionRepository;

    @InjectMocks
    BonificacionService bonificacionService;

    @Test
    void guardarBonificacion(){
        BonificacionEntity bonificacion = new BonificacionEntity(Long.valueOf("999"), Float.valueOf("4"), Float.valueOf("100"));
        Mockito.when(bonificacionRepository.save(bonificacion)).thenReturn(bonificacion);
        final BonificacionEntity resAct = bonificacionService.guardarBonificacion(bonificacion);
        assertEquals(bonificacion, resAct);
    }

    @Test
    void obtenerBonificacion(){
        BonificacionEntity bonificacion = new BonificacionEntity(Long.valueOf("999"), Float.valueOf("4"), Float.valueOf("100"));
        ArrayList<BonificacionEntity> resExp = new ArrayList<>();
        resExp.add(bonificacion);
        Mockito.when((ArrayList<BonificacionEntity>) bonificacionRepository.findAll()).thenReturn(resExp);
        final ArrayList<BonificacionEntity> resAct = bonificacionService.obtenerBonificacion();
        assertEquals(resExp, resAct);
    }

    @Test
    void sueldoBonificacionPorcentual(){
        float sueldo = 1000;
        float bonificacionPorcentual = 10;
        float resAct = bonificacionService.sueldoBonificacionPorcentual(sueldo, bonificacionPorcentual);
        float resExp = 100;
        assertEquals(resExp, resAct);
    }

    @Test
    void bonificacionAniosServicio(){
        BonificacionEntity bonificacion = new BonificacionEntity(Long.valueOf("999"), Float.valueOf("4"), Float.valueOf("100"));
        ArrayList<BonificacionEntity> resExp = new ArrayList<>();
        resExp.add(bonificacion);
        Mockito.when((ArrayList<BonificacionEntity>) bonificacionRepository.findAll()).thenReturn(resExp);
        float resAct = bonificacionService.bonificacionAniosServicio(Float.valueOf("4"));
        assertEquals(Float.valueOf("100"), resAct);
    }

}
