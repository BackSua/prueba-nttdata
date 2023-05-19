package com.prueba.crudtaks.service.Implementation;

import com.prueba.crudtaks.dto.FechaInDTO;
import com.prueba.crudtaks.dto.ResponseDTO;
import com.prueba.crudtaks.dto.TaksDTO;
import com.prueba.crudtaks.dto.TaksListDTO;
import com.prueba.crudtaks.entity.TaksEntity;
import com.prueba.crudtaks.feignClient.UserFeignClient;
import com.prueba.crudtaks.feignClient.model.UserDtoModel;
import com.prueba.crudtaks.repository.TaksRepository;
import com.prueba.crudtaks.service.ITaksService;
import com.prueba.crudtaks.utilities.Constantes;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaksService implements ITaksService {


    private final TaksRepository taksRepository;

    private final UserFeignClient userFeignClient;

    @Override
    public ResponseDTO saveTaks(TaksDTO taksDTO) throws NullPointerException {
        if (Objects.isNull(taksDTO)) {
            throw new NullPointerException("Parametro de entrada no puede llegar nulo");
        }

        ModelMapper mapper = new ModelMapper();
        TaksEntity taks = mapper.map(taksDTO, TaksEntity.class);
        taks.setStatus(Constantes.ESTADO_ASIGNDO);
        taks.setInitialDate(new Date());

        taksRepository.save(taks);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("Taks registrada con exito");

        return responseDTO;
    }

    @Override
    public ResponseDTO updateTaks(Long idTaks, TaksDTO taksDTO) throws NullPointerException {
        if (Objects.isNull(taksDTO) || idTaks == null) {
            throw new NullPointerException("Parametros de entrada no pueden ser nulos");
        }

        TaksEntity taks = taksRepository.findById(idTaks).orElseThrow(
                () -> new NullPointerException("Taks no encontrada o no existe")
        );

        ModelMapper mapper = new ModelMapper();
        TaksEntity taksUpdate = mapper.map(taks, TaksEntity.class);
        taksUpdate.setName(taksDTO.getName());
        taksUpdate.setDescription(taksDTO.getDescription());

        taksRepository.save(taksUpdate);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("Taks actualizada con exito");

        return responseDTO;
    }

    @Override
    public ResponseDTO deleteTaks(Long idTaks) throws NullPointerException {
        if (idTaks == null) {
            throw new NullPointerException("Parametros de entrada no pueden ser nulos");
        }
        taksRepository.deleteById(idTaks);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("Taks eliminado con exito");
        return responseDTO;
    }

    @Override
    public List<TaksListDTO> listTaks() throws NullPointerException {

        List<TaksEntity> listTaks = taksRepository.findAll();

        if (listTaks.isEmpty()) {
            throw new NullPointerException("Error al consultar las tareas");
        }

        ModelMapper mapper = new ModelMapper();
        List<TaksListDTO> taksListDTOS = listTaks.stream().map(
                taksEntity -> {
                    TaksListDTO taksListDTO = mapper.map(taksEntity, TaksListDTO.class);
                    return taksListDTO;
                }).collect(Collectors.toList());

        return taksListDTOS;
    }

    @Override
    public List<TaksListDTO> listTaksByIdUser(Long idUser) throws NullPointerException {
        if (idUser == null) {
            throw new NullPointerException("Parametros de entrada no pueden ser nulos");
        }

        List<TaksEntity> listTaks = taksRepository.findTaksByUserId(idUser);

        ModelMapper mapper = new ModelMapper();
        List<TaksListDTO> taksListDTOS = listTaks.stream().map(
                taksEntity -> {
                    TaksListDTO taksListDTO = mapper.map(taksEntity, TaksListDTO.class);
                    return taksListDTO;
                }).collect(Collectors.toList());

        return taksListDTOS;
    }

    @Override
    public ResponseDTO updateStatusTaks(Long idTaks) throws NullPointerException {

        if (idTaks == null) {
            throw new NullPointerException("Los valores de entrada no pueden ser nulos");
        }

        TaksEntity taks = taksRepository.findById(idTaks).orElseThrow(
                () -> new NullPointerException("Taks no encontrada o no existe")
        );

        ModelMapper mapper = new ModelMapper();

        TaksEntity taksStatus = mapper.map(taks, TaksEntity.class);
        taksStatus.setStatus(Constantes.ESTADO_FINALIZADO);
        taksStatus.setFinalDate(new Date());

        taksRepository.save(taksStatus);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("Se ha actualizao el estado de la tarea con exito");

        return responseDTO;
    }

    @Override
    public List<TaksDTO> filterByDate(FechaInDTO fechaInDTO) throws NullPointerException, ParseException {
        if (Objects.isNull(fechaInDTO)) {
            throw new NullPointerException("Los valores de entrada no pueden ser nulos");
        }

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date fechI = format.parse(fechaInDTO.getFechaI() + " 00:00:00");
        Date fechF = format.parse(fechaInDTO.getFechaF() + " 23:59:59");

        List<TaksEntity> listTaks = taksRepository.findTaksByUserIdAndFecha(fechaInDTO.getIdUser(), fechI, fechF);

        ModelMapper mapper = new ModelMapper();
        List<TaksDTO> taksListDTOS = listTaks.stream().map(
                taksEntity -> {
                    TaksDTO taksDTO = mapper.map(taksEntity, TaksDTO.class);
                    return taksDTO;
                }).collect(Collectors.toList());

        return taksListDTOS;

    }

    @Override
    public UserDtoModel findByIdUser(Long userId) throws NullPointerException {
        return userFeignClient.findByIdUser(userId);
    }
}
