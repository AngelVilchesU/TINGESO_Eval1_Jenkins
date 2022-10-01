package com.MueblesStgo.MueblesStgo;
import com.MueblesStgo.MueblesStgo.entities.AutorizacionEntity;
import com.MueblesStgo.MueblesStgo.repositories.AutorizacionRepository;
import com.MueblesStgo.MueblesStgo.services.AutorizacionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class AutorizacionServiceTest {
    @Mock
    private AutorizacionRepository autorizacionRepository;

    @InjectMocks
    AutorizacionService autorizacionService;

    @Test
    void guardarAutorizacion(){
        AutorizacionEntity autorizacion = new AutorizacionEntity(Long.valueOf("999"), LocalDate.of(2022,9,20), "12.345.678-9", Float.valueOf("2"), null);
        Mockito.when(autorizacionRepository.save(autorizacion)).thenReturn(autorizacion);
        final AutorizacionEntity resAct = autorizacionService.guardarAutorizacion(autorizacion);
        assertEquals(autorizacion, resAct);
    }

    @Test
    void obtenerAutorizacion(){
        AutorizacionEntity autorizacion = new AutorizacionEntity(Long.valueOf("999"), LocalDate.of(2022,9,20), "12.345.678-9", Float.valueOf("2"), null);
        ArrayList<AutorizacionEntity> resExp = new ArrayList<>();
        resExp.add(autorizacion);
        Mockito.when((ArrayList<AutorizacionEntity>) autorizacionRepository.findAll()).thenReturn(resExp);
        final ArrayList<AutorizacionEntity> resAct = autorizacionService.obtenerAutorizaciones();
        assertEquals(resExp, resAct);
    }

    @Test
    void fechaFormato(){
        String fechaStr = "2022-12-02";
        LocalDate resAct = autorizacionService.fechaFormato(fechaStr);
        LocalDate resExp = LocalDate.of(2022, 12, 2);
        assertEquals(resExp, resAct);
    }

    @Test
    void tieneAutorizacion(){
        AutorizacionEntity autorizacion = new AutorizacionEntity(Long.valueOf("999"), LocalDate.of(2022,9,20), "12.345.678-9", Float.valueOf("2"), null);
        ArrayList<AutorizacionEntity> resExp = new ArrayList<>();
        resExp.add(autorizacion);
        Mockito.when((ArrayList<AutorizacionEntity>) autorizacionRepository.findAll()).thenReturn(resExp);
        boolean resAct = autorizacionService.tieneAutorizacion(LocalDate.of(2022,9,20), "12.345.678-9");
        assertEquals(true, resAct);
    }

    @Test
    void noTieneAutorizacion(){
        AutorizacionEntity autorizacion = new AutorizacionEntity(Long.valueOf("999"), LocalDate.of(2022,9,20), "12.345.678-9", Float.valueOf("2"), null);
        ArrayList<AutorizacionEntity> resExp = new ArrayList<>();
        resExp.add(autorizacion);
        Mockito.when((ArrayList<AutorizacionEntity>) autorizacionRepository.findAll()).thenReturn(resExp);
        boolean resAct = autorizacionService.tieneAutorizacion(LocalDate.of(2022,9,20), "12.345.678-8");
        assertEquals(false, resAct);
    }

    @Test
    void horasExtra(){
        AutorizacionEntity autorizacion = new AutorizacionEntity(Long.valueOf("999"), LocalDate.of(2022,9,20), "12.345.678-9", Float.valueOf("2"), null);
        ArrayList<AutorizacionEntity> resExp = new ArrayList<>();
        resExp.add(autorizacion);
        Mockito.when((ArrayList<AutorizacionEntity>) autorizacionRepository.findAll()).thenReturn(resExp);
        float resAct = autorizacionService.horasExtra(LocalDate.of(2022,9,20), "12.345.678-9");
        assertEquals(Float.valueOf("2"), resAct);
    }

    @Test
    void noHorasExtra(){
        AutorizacionEntity autorizacion = new AutorizacionEntity(Long.valueOf("999"), LocalDate.of(2022,9,20), "12.345.678-9", Float.valueOf("2"), null);
        ArrayList<AutorizacionEntity> resExp = new ArrayList<>();
        resExp.add(autorizacion);
        Mockito.when((ArrayList<AutorizacionEntity>) autorizacionRepository.findAll()).thenReturn(resExp);
        float resAct = autorizacionService.horasExtra(LocalDate.of(2022,9,20), "12.345.678-8");
        assertEquals(Float.valueOf("0"), resAct);
    }
}
