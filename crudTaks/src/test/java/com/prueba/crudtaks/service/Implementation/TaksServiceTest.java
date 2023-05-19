package com.prueba.crudtaks.service.Implementation;

import com.prueba.crudtaks.dto.FechaInDTO;
import com.prueba.crudtaks.dto.ResponseDTO;
import com.prueba.crudtaks.dto.TaksDTO;
import com.prueba.crudtaks.dto.TaksListDTO;
import com.prueba.crudtaks.entity.TaksEntity;
import com.prueba.crudtaks.repository.TaksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaksServiceTest {

    @Mock
    TaksRepository taksRepository;

    @InjectMocks
    TaksService taksService;

    @Test
    void saveTaks() {

        TaksDTO taksDTO = new TaksDTO();

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("Taks registrada con exito");

        Mockito.when(taksRepository.save(Mockito.any()))
                .thenReturn(new TaksEntity());

        var result = taksService.saveTaks(taksDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Taks registrada con exito",
                responseDTO.getResponse());

        Mockito.verify(taksRepository, Mockito.times(1))
                .save(Mockito.any(TaksEntity.class));

    }

    @Test
    void updateTaks() {

        Long idTaks = 123L;

        TaksDTO taksDTO = new TaksDTO();
        taksDTO.setDescription("ABC");
        taksDTO.setName("ABC");
        taksDTO.setUserId(123L);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("Taks actualizada con exito");

        TaksEntity taks = Mockito.mock(TaksEntity.class);

        Mockito.when(taksRepository.findById(idTaks))
                .thenReturn(Optional.of(taks));

        Mockito.when(taksRepository.save(Mockito.any()))
                .thenReturn(new TaksEntity());

        var result = taksService.updateTaks(idTaks, taksDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Taks actualizada con exito",
                responseDTO.getResponse());
    }

    @Test
    void deleteTaks() {

        Long idTaks = 1234L;

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("Taks eliminado con exito");

        var response = taksService.deleteTaks(idTaks);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Taks eliminado con exito",
                responseDTO.getResponse());

        Mockito.verify(taksRepository, Mockito.times(1))
                .deleteById(idTaks);

    }

    @Test
    void listTaks() {

        TaksEntity taks = new TaksEntity();

        List<TaksEntity> listTaks = new ArrayList<>();
        listTaks.add(taks);

        TaksListDTO taksListDTO = new TaksListDTO();

        List<TaksListDTO> taksListDTOS = new ArrayList<>();
        taksListDTOS.add(taksListDTO);

        Mockito.when(taksRepository.findAll())
                .thenReturn(listTaks);

        var result = taksService.listTaks();

        Assertions.assertNotNull(result);

    }

    @Test
    void listTaksByIdUser() {

        Long idUser = 1234L;

        TaksEntity taks = new TaksEntity();

        List<TaksEntity> listTaks = new ArrayList<>();
        listTaks.add(taks);

        TaksListDTO taksListDTO = new TaksListDTO();

        List<TaksListDTO> taksListDTOS = new ArrayList<>();
        taksListDTOS.add(taksListDTO);

        Mockito.when(taksRepository.findTaksByUserId(idUser))
                .thenReturn(listTaks);

        var result = taksService.listTaksByIdUser(idUser);

        Assertions.assertNotNull(result);

    }

    @Test
    void updateStatusTaks() {

        Long idTaks = 1234L;

        TaksEntity taks = new TaksEntity();

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("Se ha actualizao el estado de la tarea con exito");

        Mockito.when(taksRepository.findById(idTaks))
                .thenReturn(Optional.of(taks));

        var result = taksService.updateStatusTaks(idTaks);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Se ha actualizao el estado de la tarea con exito",
                responseDTO.getResponse());

    }
    @Test
    void filterByDate() throws ParseException {

        FechaInDTO fechaInDTO = new FechaInDTO();
        fechaInDTO.setFechaI("17/05/2023");
        fechaInDTO.setFechaF("18/05/2023");
        fechaInDTO.setIdUser(2L);

        TaksEntity taks = new TaksEntity();

        List<TaksEntity> listTaks = new ArrayList<>();
        listTaks.add(taks);

        TaksListDTO taksListDTO = new TaksListDTO();

        List<TaksListDTO> taksListDTOS = new ArrayList<>();
        taksListDTOS.add(taksListDTO);

        Mockito.when(taksRepository.findTaksByUserIdAndFecha(Mockito.anyLong(),Mockito.any(),Mockito.any()))
                .thenAnswer(invocation -> {
                    Long idUser = invocation.getArgument(0);
                    Date fechaI = invocation.getArgument(1);
                    Date fechaF = invocation.getArgument(2);

                    return listTaks;
                });

        var result = taksService.filterByDate(fechaInDTO);

        Assertions.assertNotNull(result);

    }

}