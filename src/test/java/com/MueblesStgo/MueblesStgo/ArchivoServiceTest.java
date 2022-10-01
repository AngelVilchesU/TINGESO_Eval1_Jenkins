package com.MueblesStgo.MueblesStgo;

import com.MueblesStgo.MueblesStgo.entities.ArchivoEntity;
import com.MueblesStgo.MueblesStgo.repositories.ArchivoRepository;
import com.MueblesStgo.MueblesStgo.services.ArchivoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class ArchivoServiceTest {

    @Mock
    private ArchivoRepository archivoRepository;

    @InjectMocks
    ArchivoService archivoService;

    @Test
    void guardarMarca(){
        ArchivoEntity archivo = new ArchivoEntity(LocalDate.of(2022, 9,20), LocalTime.of(16,31,00), "12.345.678-9",null);
        Mockito.when(archivoRepository.save(archivo)).thenReturn(archivo);
        final ArchivoEntity resAct = archivoService.guardarMarca(archivo);
        assertEquals(archivo, resAct);
    }

    @Test
    void obtenerMarca(){
        ArchivoEntity archivo = new ArchivoEntity(LocalDate.of(2022, 9,20), LocalTime.of(16,31,00), "12.345.678-9",null);
        ArrayList<ArchivoEntity> resExp = new ArrayList<>();
        resExp.add(archivo);
        Mockito.when((ArrayList<ArchivoEntity>) archivoRepository.findAll()).thenReturn(resExp);
        final ArrayList<ArchivoEntity> resAct = archivoService.obtenerMarcas();
        assertEquals(resExp, resAct);
    }

    @Test
    void cargarArchivo(){
        MockMultipartFile archivo = new MockMultipartFile("name.txt", "".getBytes());
        String resAct = archivoService.cargarArchivo(archivo);
        assertEquals("El archivo no se ha subido exitosamente", resAct);
    }

    @Test
    void cargarArchivo1(){
        MockMultipartFile archivo = new MockMultipartFile("name.txt", "2022/09/01;08:00;11.111.111-1".getBytes());
        String resAct = archivoService.cargarArchivo(archivo);
        assertEquals("El archivo se ha subido exitosamente", resAct);
    }

    @Test
    void crearDirectorio(){
        assertEquals(false, archivoService.crearDirectorio());
    }
}
