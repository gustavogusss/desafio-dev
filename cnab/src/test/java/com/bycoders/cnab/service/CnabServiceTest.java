package com.bycoders.cnab.service;

import com.bycoders.cnab.controller.CnabController;
import com.bycoders.cnab.dto.FaturamentoDTO;
import com.bycoders.cnab.entity.Cnab;
import com.bycoders.cnab.enums.TipoTransacao;
import com.bycoders.cnab.repository.CnabRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CnabServiceTest {

    @InjectMocks
    CnabService cnabService;

    @Mock
    CnabRepository cnabRepository;

    private List<Cnab> getDataSum500(){
        List<Cnab> list = new ArrayList<>();
        Cnab c1 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(100), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c2 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(100), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c3 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(100), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c4 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(100), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c5 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(100), "123321", "987789", LocalTime.now(), "joao", "joao");
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        return list;
    }

    private List<Cnab> getNegativeDataSum(){
        List<Cnab> list = new ArrayList<>();
        Cnab c1 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(-100), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c2 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(-100), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c3 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(-100), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c4 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(-100), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c5 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(-100), "123321", "987789", LocalTime.now(), "joao", "joao");
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        return list;
    }

    private List<Cnab> getRandomDataSum(){
        List<Cnab> list = new ArrayList<>();
        Cnab c1 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(-1000), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c2 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(1023), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c3 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(-100), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c4 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(50), "123321", "987789", LocalTime.now(), "joao", "joao");
        Cnab c5 = new Cnab(TipoTransacao.DEBITO, LocalDate.now(), new BigDecimal(50), "123321", "987789", LocalTime.now(), "joao", "joao");
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        return list;
    }


    @Test
    void DeveRetornarSomaCnabsIgual500(){
        when(cnabRepository.findAll()).thenReturn(getDataSum500());
        List<FaturamentoDTO> faturamentoDTOS = cnabService.listarAbatimento();
        assertEquals(faturamentoDTOS.get(0).getValor(), new BigDecimal(500));
    }

    @Test
    void DeveRetornarSomaNegativaCnabs(){
        when(cnabRepository.findAll()).thenReturn(getNegativeDataSum());
        List<FaturamentoDTO> faturamentoDTOS = cnabService.listarAbatimento();
        assertEquals(faturamentoDTOS.get(0).getValor(), new BigDecimal(-500));
    }

    @Test
    void DeveRetornarSomaAleatoriaIgualA23(){
        when(cnabRepository.findAll()).thenReturn(getRandomDataSum());
        List<FaturamentoDTO> faturamentoDTOS = cnabService.listarAbatimento();
        assertEquals(faturamentoDTOS.get(0).getValor(), new BigDecimal(23));
    }
}