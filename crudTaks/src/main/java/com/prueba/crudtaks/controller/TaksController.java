package com.prueba.crudtaks.controller;


import com.prueba.crudtaks.dto.FechaInDTO;
import com.prueba.crudtaks.dto.ResponseDTO;
import com.prueba.crudtaks.dto.TaksDTO;
import com.prueba.crudtaks.dto.TaksListDTO;
import com.prueba.crudtaks.feignClient.model.UserDtoModel;
import com.prueba.crudtaks.service.ITaksService;
import com.prueba.crudtaks.utilities.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constantes.PATH_TAKS)
@Api(description = "Consultas taks", tags = "Taks Api")
public class TaksController {


    private final ITaksService taksService;

    /**
     * Guardar tarea
     *
     * @param taksDTO Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    @PostMapping
    @ApiOperation(value = "Guadar Taks")
    public ResponseDTO saveTaks(@RequestBody TaksDTO taksDTO) throws NullPointerException {
        return taksService.saveTaks(taksDTO);
    }

    /**
     * Actualizar tarea
     *
     * @param idTaks  Parametro de entrada
     * @param taksDTO Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    @PutMapping(value = Constantes.PATH_TAKS_ID)
    @ApiOperation(value = "Actualizar Taks")
    public ResponseDTO updateTaks(
            @PathVariable("idTaks") Long idTaks,
            @RequestBody TaksDTO taksDTO) throws NullPointerException {
        return taksService.updateTaks(idTaks, taksDTO);
    }

    /**
     * Eliminar tarea
     *
     * @param idTaks Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    @DeleteMapping(value = Constantes.PATH_TAKS_ID)
    @ApiOperation(value = "Eliminar Taks")
    public ResponseDTO deleteTaks(@PathVariable("idTaks") Long idTaks) throws NullPointerException {
        return taksService.deleteTaks(idTaks);
    }

    /**
     * Lista de las tareas
     *
     * @return List<TaksListDTO>
     * @throws NullPointerException Error
     */
    @GetMapping
    @ApiOperation(value = "Lista de taks")
    public List<TaksListDTO> listTaks() throws NullPointerException {
        return taksService.listTaks();
    }

    /**
     * Lista de las tareas por usuario
     *
     * @param idUser Parametro de entrada
     * @return List<TaksListDTO>
     * @throws NullPointerException Error
     */
    @GetMapping(value = Constantes.PATH_USER_ID)
    @ApiOperation(value = "Lista de taks por id se usuario")
    public List<TaksListDTO> listTaksByIdUser(@PathVariable("idUser") Long idUser) throws NullPointerException {
        return taksService.listTaksByIdUser(idUser);
    }

    /**
     * Actaulizar esatdo de tarea a finalizado
     *
     * @param idTaks Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    @PostMapping(value = Constantes.PATH_TAKS_ACTUA_ID_TASK)
    @ApiOperation(value = "Actualizar el estado de la tarea")
    public ResponseDTO updateStatusTaks(@PathVariable("idTaks") Long idTaks) throws NullPointerException {
        return taksService.updateStatusTaks(idTaks);
    }

    /**
     * Filtrar tarea por fecha de registro y id de usuario
     *
     * @param fechaInDTO Parametro de entrada
     * @return List<TaksDTO>
     * @throws NullPointerException Error
     * @throws ParseException Error
     */
    @PostMapping(value = Constantes.TASK_PATH_FECHA)
    @ApiOperation(value = "Filtrar tarea por fecha de registro y id de usuario")
    public List<TaksDTO> filterByDate(@RequestBody FechaInDTO fechaInDTO) throws NullPointerException, ParseException {
        return taksService.filterByDate(fechaInDTO);
    }

    @GetMapping("usuario/{userId}")
    public UserDtoModel findByIdUser(@PathVariable Long userId) throws NullPointerException{
        return taksService.findByIdUser(userId);
    }
}
