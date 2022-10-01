package com.MueblesStgo.MueblesStgo;

import com.MueblesStgo.MueblesStgo.entities.EmpleadoEntity;
import com.MueblesStgo.MueblesStgo.repositories.EmpleadoRepository;
import com.MueblesStgo.MueblesStgo.services.EmpleadoService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    EmpleadoService empleadoService;

    @Test
    void guardarEmpleado(){
        EmpleadoEntity empleado = new EmpleadoEntity(Long.valueOf("999"), "12.345.678-9", "Nombre", "Apellido", LocalDate.of(2000, 10,8), LocalDate.of(2022, 9,20));
        Mockito.when(empleadoRepository.save(empleado)).thenReturn(empleado);
        final EmpleadoEntity resAct = empleadoService.guardarEmpleado(empleado);
        assertEquals(empleado, resAct);
    }

    @Test
    void obtenerEmpleado(){
        EmpleadoEntity empleado = new EmpleadoEntity(Long.valueOf("999"), "12.345.678-9", "Nombre", "Apellido", LocalDate.of(2000, 10,8), LocalDate.of(2022, 9,20));
        ArrayList<EmpleadoEntity> resExp = new ArrayList<>();
        resExp.add(empleado);
        Mockito.when((ArrayList<EmpleadoEntity>) empleadoRepository.findAll()).thenReturn(resExp);
        final ArrayList<EmpleadoEntity> resAct = empleadoService.obtenerEmpleados();
        assertEquals(resExp, resAct);
    }

    @Test
    void obtenerPorRut(){
        EmpleadoEntity empleado = new EmpleadoEntity(Long.valueOf("999"), "12.345.678-9", "Nombre", "Apellido", LocalDate.of(2000, 10,8), LocalDate.of(2022, 9,20));
        Mockito.when(empleadoRepository.findByRut("12.345.678-9")).thenReturn(empleado);
        final EmpleadoEntity resAct = empleadoService.obtenerPorRut("12.345.678-9");
        assertEquals(empleado, resAct);
    }
}
