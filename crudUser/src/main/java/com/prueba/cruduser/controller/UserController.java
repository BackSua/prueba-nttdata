package com.prueba.cruduser.controller;

import com.prueba.cruduser.dto.ResponseDTO;
import com.prueba.cruduser.dto.UserDTO;
import com.prueba.cruduser.dto.UserListDTO;
import com.prueba.cruduser.service.IUserService;
import com.prueba.cruduser.utilities.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constantes.PATH_USER)
@Api(description = "Consultas user", tags = "User Api")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * Guardar un usuario
     *
     * @param userDTO Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    @PostMapping
    @ApiOperation(value = "Guadar User")
    public ResponseDTO saveUser(@RequestBody UserDTO userDTO) throws NullPointerException {
        return userService.saveUser(userDTO);
    }

    /**
     * Actualizar informacion del usuario
     *
     * @param idUser  Id del usuario a eliminar
     * @param userDTO Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException
     */
    @PutMapping(value = Constantes.PATH_USER_ID)
    @ApiOperation(value = "Actualizar User")
    public ResponseDTO updateUser(@PathVariable("idUser") Long idUser,
                                  @RequestBody UserDTO userDTO) throws NullPointerException {
        return userService.updateUser(idUser, userDTO);
    }

    /**
     * Eliminar usuario
     *
     * @param idUser Id del usuario a eliminar
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    @DeleteMapping(value = Constantes.PATH_USER_ID)
    @ApiOperation(value = "Eliminar User")
    public ResponseDTO deleteUser(@PathVariable("idUser") Long idUser) throws NullPointerException {
        return userService.deleteUser(idUser);
    }

    /**
     * Lista de usuarios
     *
     * @return Lista de usuarios
     * @throws NullPointerException Error
     */
    @GetMapping
    @ApiOperation(value = "Lista de User")
    public List<UserListDTO> listUsers() throws NullPointerException {
        return userService.listUsers();
    }

    /**
     * Informacion del usaurio por ID
     * @param idUser Parametro de entrada
     * @return UserListDTO
     * @throws NullPointerException Error
     */
    @GetMapping(value = Constantes.PATH_USER_ID)
    @ApiOperation(value = "User por id")
    public UserListDTO findByIdUser(@PathVariable ("idUser") Long idUser) throws NullPointerException{
        return userService.findByIdUser(idUser);
    }
}
