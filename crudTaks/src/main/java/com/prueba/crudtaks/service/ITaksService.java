package com.prueba.crudtaks.service;


import com.prueba.crudtaks.dto.FechaInDTO;
import com.prueba.crudtaks.dto.ResponseDTO;
import com.prueba.crudtaks.dto.TaksDTO;
import com.prueba.crudtaks.dto.TaksListDTO;
import com.prueba.crudtaks.feignClient.model.UserDtoModel;

import java.text.ParseException;
import java.util.List;

public interface ITaksService {

    /**
     * Guardar tarea
     *
     * @param taksDTO Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    ResponseDTO saveTaks(TaksDTO taksDTO) throws NullPointerException;

    /**
     * Actualizar tarea
     *
     * @param idTaks  Parametro de entrada
     * @param taksDTO Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    ResponseDTO updateTaks(Long idTaks, TaksDTO taksDTO) throws NullPointerException;

    /**
     * Eliminar tarea
     *
     * @param idTaks Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    ResponseDTO deleteTaks(Long idTaks) throws NullPointerException;

    /**
     * Lista de las tareas
     *
     * @return List<TaksListDTO>
     * @throws NullPointerException Error
     */
    List<TaksListDTO> listTaks() throws NullPointerException;

    /**
     * Lista de las tareas por usuario
     *
     * @param idUser Parametro de entrada
     * @return List<TaksListDTO>
     * @throws NullPointerException Error
     */
    List<TaksListDTO> listTaksByIdUser(Long idUser) throws NullPointerException;

    /**
     * Actaulizar esatdo de tarea a finalizado
     *
     * @param idTaks Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    ResponseDTO updateStatusTaks(Long idTaks) throws NullPointerException;

    /**
     * Filtrar tarea por fecha de registro y id de usuario
     *
     * @param fechaInDTO Parametro de entrada
     * @return List<TaksDTO>
     * @throws NullPointerException Error
     * @throws ParseException Error
     */
    List<TaksDTO> filterByDate(FechaInDTO fechaInDTO) throws NullPointerException, ParseException;


    UserDtoModel findByIdUser(Long userId) throws NullPointerException;

}
