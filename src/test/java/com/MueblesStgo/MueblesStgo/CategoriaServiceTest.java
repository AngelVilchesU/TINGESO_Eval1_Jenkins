package com.MueblesStgo.MueblesStgo;

import com.MueblesStgo.MueblesStgo.entities.CategoriaEntity;
import com.MueblesStgo.MueblesStgo.repositories.CategoriaRepository;
import com.MueblesStgo.MueblesStgo.services.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;
    @InjectMocks
    CategoriaService categoriaService;

    @Test
    void guardarCategoria(){
        CategoriaEntity categoria = new CategoriaEntity(Long.valueOf("999"), 'A', Float.valueOf("1000"), Float.valueOf("200"));
        Mockito.when(categoriaRepository.save(categoria)).thenReturn(categoria);
        final CategoriaEntity resAct = categoriaService.guardarCategoria(categoria);
        assertEquals(categoria, resAct);
    }

    @Test
    void obtenerCategoria(){
        CategoriaEntity categoria = new CategoriaEntity(Long.valueOf("999"), 'A', Float.valueOf("1000"), Float.valueOf("200"));
        ArrayList<CategoriaEntity> resExp = new ArrayList<>();
        resExp.add(categoria);
        Mockito.when((ArrayList<CategoriaEntity>) categoriaRepository.findAll()).thenReturn(resExp);
        final ArrayList<CategoriaEntity> resAct = categoriaService.obtenerCategoria();
        assertEquals(resExp, resAct);
    }

    @Test
    void pagoHorasExtra(){
        CategoriaEntity categoria = new CategoriaEntity(Long.valueOf("999"), 'A', Float.valueOf("1000"), Float.valueOf("200"));
        ArrayList<CategoriaEntity> resExp = new ArrayList<>();
        resExp.add(categoria);
        Mockito.when((ArrayList<CategoriaEntity>) categoriaRepository.findAll()).thenReturn(resExp);
        float resAct = categoriaService.pagoHorasExtra(Float.valueOf("2"), 'A');
        assertEquals(Float.valueOf("400"), resAct);
    }

    @Test
    void noPagoHorasExtra(){
        CategoriaEntity categoria = new CategoriaEntity(Long.valueOf("999"), 'A', Float.valueOf("1000"), Float.valueOf("200"));
        ArrayList<CategoriaEntity> resExp = new ArrayList<>();
        resExp.add(categoria);
        Mockito.when((ArrayList<CategoriaEntity>) categoriaRepository.findAll()).thenReturn(resExp);
        float resAct = categoriaService.pagoHorasExtra(Float.valueOf("0"), 'B');
        assertEquals(Float.valueOf("0"), resAct);
    }
}
