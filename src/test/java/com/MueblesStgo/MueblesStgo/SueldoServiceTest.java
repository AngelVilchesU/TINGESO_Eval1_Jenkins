package com.MueblesStgo.MueblesStgo;
import com.MueblesStgo.MueblesStgo.entities.SueldoEntity;
import com.MueblesStgo.MueblesStgo.repositories.SueldoRepository;
import com.MueblesStgo.MueblesStgo.services.SueldoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockRequestDispatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class SueldoServiceTest {
    private MockMvc mockMvc;
    @Mock
    private SueldoRepository sueldoRepository;

    @InjectMocks
    SueldoService sueldoService;

    @Test
    void guardarSueldo(){
        SueldoEntity sueldo = new SueldoEntity("12.345.678-9", "Nombre Apellido",
                'A', Float.valueOf(2), Float.valueOf("1000"), Float.valueOf("100"),
                Float.valueOf("20"), Float.valueOf("20"), Float.valueOf(1100),
                Float.valueOf("10"), Float.valueOf("8"), Float.valueOf("900"),
                LocalDate.of(2022,9,20));
        Mockito.when(sueldoRepository.save(sueldo)).thenReturn(sueldo);
        final SueldoEntity resAct = sueldoService.guardarSueldo(sueldo);
        assertEquals(sueldo, resAct);
    }

    @Test
    void obtenerSueldo(){
        SueldoEntity sueldo = new SueldoEntity("12.345.678-9", "Nombre Apellido",
                'A', Float.valueOf(2), Float.valueOf("1000"), Float.valueOf("100"),
                Float.valueOf("20"), Float.valueOf("20"), Float.valueOf(1100),
                Float.valueOf("10"), Float.valueOf("8"), Float.valueOf("900"),
                LocalDate.of(2022,9,20));
        ArrayList<SueldoEntity> resExp = new ArrayList<>();
        resExp.add(sueldo);
        Mockito.when((ArrayList<SueldoEntity>) sueldoRepository.findAll()).thenReturn(resExp);
        final ArrayList<SueldoEntity> resAct = sueldoService.obtenerSueldo();
        assertEquals(resExp, resAct);
    }

    @Test
    void esDiaDeSemana(){
        int dia = 26;
        int mes = 9;
        int anio = 2022;
        boolean resAct = sueldoService.esDiaDeSemana(dia, mes, anio);
        boolean resExp = true;
        assertEquals(resExp, resAct);
    }

    @Test
    void noEsDiaDeSemana(){
        int dia = 25;
        int mes = 9;
        int anio = 2022;
        boolean resAct = sueldoService.esDiaDeSemana(dia, mes, anio);
        boolean resExp = false;
        assertEquals(resExp, resAct);
    }

    @Test
    void esBisiesto(){
        int anio = 2020;
        boolean resAct = sueldoService.esBisiesto(anio);
        boolean resExp = true;
        assertEquals(resExp, resAct);
    }

    @Test
    void noEsBisiesto(){
        int anio = 2022;
        boolean resAct = sueldoService.esBisiesto(anio);
        boolean resExp = false;
        assertEquals(resExp, resAct);
    }

    @Test
    void diasDelMes30(){
        int resAct = sueldoService.diasDelMes(9, 2022);
        assertEquals(30, resAct);
    }

    @Test
    void diasDelMes31(){
        int resAct = sueldoService.diasDelMes(10, 2022);
        assertEquals(31, resAct);
    }

    @Test
    void diasDelMes28(){
        int resAct = sueldoService.diasDelMes(2, 2023);
        assertEquals(28, resAct);
    }

    @Test
    void diasDelMes29(){
        int resAct = sueldoService.diasDelMes(2, 2024);
        assertEquals(29, resAct);
    }

    /*
    @Test
    public void calculoPlanillas(){
        SueldoEntity sueldo = new SueldoEntity("12.345.678-9", "Nombre Apellido",
                'A', Float.valueOf(2), Float.valueOf("1000"), Float.valueOf("100"),
                Float.valueOf("20"), Float.valueOf("20"), Float.valueOf(1100),
                Float.valueOf("10"), Float.valueOf("8"), Float.valueOf("900"),
                LocalDate.of(2022,9,20));
        ArrayList<SueldoEntity> resExp = new ArrayList<>();
        resExp.add(sueldo);
        Mockito.when((ArrayList<SueldoEntity>) sueldoRepository.findAll()).thenReturn(resExp);
        //MockMultipartFile archivo = new MockMultipartFile("name.txt", "2022/09/01;08:00;11.111.111-1".getBytes());

        try {
            ResultActions mo = mockMvc.perform(MockMvcRequestBuilders.multipart("/carpeta")
                    .file(new MockMultipartFile("name.txt", "2022/09/01;08:00;11.111.111-1".getBytes())))
                    .andExpect(status().isOk());
            String mock = String.valueOf(mo);
            String ruta = String.valueOf(mock);
            String resAct = sueldoService.calculoPlanillas(ruta,"name.txt");
            assertEquals("El calculo se ha realizado existosamente", resAct);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }






    }

    @Test
    public void mostrarSueldos(){
        SueldoEntity sueldo = new SueldoEntity("12.345.678-9", "Nombre Apellido",
                'A', Float.valueOf(2), Float.valueOf("1000"), Float.valueOf("100"),
                Float.valueOf("20"), Float.valueOf("20"), Float.valueOf(1100),
                Float.valueOf("10"), Float.valueOf("8"), Float.valueOf("900"),
                LocalDate.of(2022,9,20));
        ArrayList<SueldoEntity> resExp = new ArrayList<>();
        resExp.add(sueldo);
        Mockito.when((ArrayList<SueldoEntity>) sueldoRepository.findAll()).thenReturn(resExp);
        String resAct = sueldoService.mostrarSueldos(9, 2022);
        assertEquals("Reporte generado exitosamente", resAct);
    }
     */
}
