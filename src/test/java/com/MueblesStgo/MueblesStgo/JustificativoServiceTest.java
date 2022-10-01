package com.MueblesStgo.MueblesStgo;

import com.MueblesStgo.MueblesStgo.entities.JustificativoEntity;
import com.MueblesStgo.MueblesStgo.repositories.JustificativoRepository;
import com.MueblesStgo.MueblesStgo.services.JustificativoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class JustificativoServiceTest {
    @Mock
    private JustificativoRepository justificativoRepository;

    @InjectMocks
    JustificativoService justificativoService;

    @Test
    void guardarJustificativo(){
        JustificativoEntity justificativo = new JustificativoEntity(Long.valueOf("999"), LocalDate.of(2022,9,20), "12.345.678-9");
        Mockito.when(justificativoRepository.save(justificativo)).thenReturn(justificativo);
        final JustificativoEntity resAct = justificativoService.guardarJustificativo(justificativo);
        assertEquals(justificativo, resAct);
    }

    @Test
    void obtenerJustificativo(){
        JustificativoEntity justificativo = new JustificativoEntity(Long.valueOf("999"), LocalDate.of(2022,9,20), "12.345.678-9");
        ArrayList<JustificativoEntity> resExp = new ArrayList<>();
        resExp.add(justificativo);
        Mockito.when((ArrayList<JustificativoEntity>) justificativoRepository.findAll()).thenReturn(resExp);
        final ArrayList<JustificativoEntity> resAct = justificativoService.obtenerJustificativos();
        assertEquals(resExp, resAct);
    }

    @Test
    void fechaFormato(){
        String fechaStr = "2022-09-20";
        LocalDate resAct = justificativoService.fechaFormato(fechaStr);
        LocalDate resExp = LocalDate.of(2022, 9, 20);
        assertEquals(resExp, resAct);
    }

    @Test
    void estaJustificado(){
        JustificativoEntity justificativo = new JustificativoEntity(Long.valueOf("999"), LocalDate.of(2022,9,20), "12.345.678-9");
        ArrayList<JustificativoEntity> resExp = new ArrayList<>();
        resExp.add(justificativo);
        Mockito.when((ArrayList<JustificativoEntity>) justificativoRepository.findAll()).thenReturn(resExp);
        boolean resAct = justificativoService.estaJustificado(LocalDate.of(2022, 9,20), "12.345.678-9");
        assertEquals(true, resAct);
    }
}
