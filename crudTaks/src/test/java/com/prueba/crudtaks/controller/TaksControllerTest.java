package com.prueba.crudtaks.controller;

import com.prueba.crudtaks.dto.FechaInDTO;
import com.prueba.crudtaks.dto.ResponseDTO;
import com.prueba.crudtaks.dto.TaksDTO;
import com.prueba.crudtaks.dto.TaksListDTO;
import com.prueba.crudtaks.service.Implementation.TaksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaksControllerTest {

    @Mock
    TaksService taksService;
    @InjectMocks
    TaksController taksController;

    @Test
    void saveUser() {

        ResponseDTO response = new ResponseDTO();

        TaksDTO taksDTO = new TaksDTO();

        Mockito.when(taksService.saveTaks(taksDTO))
                .thenReturn(response);

        var respon = taksController.saveTaks(taksDTO);

        Assertions.assertNotNull(respon);

    }

    @Test
    void updateUser() {

        Long idTaks = 1234L;

        ResponseDTO response = new ResponseDTO();

        TaksDTO taksDTO = new TaksDTO();

        Mockito.when(taksService.updateTaks(idTaks, taksDTO))
                .thenReturn(response);

        var respon = taksController.updateTaks(idTaks, taksDTO);

        Assertions.assertNotNull(respon);

    }

    @Test
    void deleteUser() {

        ResponseDTO response = new ResponseDTO();

        Long idTaks = 1234L;

        Mockito.when(taksService.deleteTaks(idTaks))
                .thenReturn(response);

        var respon = taksController.deleteTaks(idTaks);

        Assertions.assertNotNull(respon);

    }

    @Test
    void listTaks() {

        TaksListDTO taksListDTO = new TaksListDTO();

        List<TaksListDTO> taksListDTOS = new ArrayList<>();
        taksListDTOS.add(taksListDTO);

        Mockito.when(taksService.listTaks())
                .thenReturn(taksListDTOS);

        var respon = taksController.listTaks();

        Assertions.assertNotNull(respon);

    }

    @Test
    void listTaksByIdUser() {

        Long idUser = 123L;

        TaksListDTO taksListDTO = new TaksListDTO();

        List<TaksListDTO> taksListDTOS = new ArrayList<>();
        taksListDTOS.add(taksListDTO);

        Mockito.when(taksService.listTaksByIdUser(idUser))
                .thenReturn(taksListDTOS);

        var respon = taksController.listTaksByIdUser(idUser);

        Assertions.assertNotNull(respon);

    }

    @Test
    void updateStatusTaks() {

        Long idTaks = 123L;

        ResponseDTO responseDTO = new ResponseDTO();

        Mockito.when(taksService.updateStatusTaks(idTaks))
                .thenReturn(responseDTO);

        var respon = taksController.updateStatusTaks(idTaks);

        Assertions.assertNotNull(respon);

    }

    @Test
    void filterByDate() throws NullPointerException, ParseException {

        FechaInDTO fechaInDTO = new FechaInDTO();
        fechaInDTO.setFechaI("17/05/2023");
        fechaInDTO.setFechaF("18/05/2023");
        fechaInDTO.setIdUser(2L);

        TaksDTO taksDTO = new TaksDTO();

        List<TaksDTO> taksDTOS = new ArrayList<>();
        taksDTOS.add(taksDTO);

        Mockito.when(taksService.filterByDate(fechaInDTO))
              .thenReturn(taksDTOS);

        var respon = taksController.filterByDate(fechaInDTO);

        Assertions.assertNotNull(respon);
    }
}