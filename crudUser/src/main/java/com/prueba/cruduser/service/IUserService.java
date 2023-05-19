package com.prueba.cruduser.service;

import com.prueba.cruduser.dto.ResponseDTO;
import com.prueba.cruduser.dto.UserDTO;
import com.prueba.cruduser.dto.UserListDTO;

import java.util.List;

public interface IUserService {
    /**
     * Guardar un usuario
     *
     * @param userDTO Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    ResponseDTO saveUser(UserDTO userDTO) throws NullPointerException;

    /**
     * Actualizar informacion del usuario
     *
     * @param idUser  Id del usuario a actualizar
     * @param userDTO Parametro de entrada
     * @return ResponseDTO
     * @throws NullPointerException
     */
    ResponseDTO updateUser(Long idUser, UserDTO userDTO) throws NullPointerException;

    /**
     * Eliminar usuario
     * @param idUser Id del usuario a eliminar
     * @return ResponseDTO
     * @throws NullPointerException Error
     */
    ResponseDTO deleteUser(Long idUser) throws NullPointerException;

    /**
     * Lista de usuarios
     * @return Lista de usuarios
     * @throws NullPointerException Error
     */
    List<UserListDTO> listUsers() throws NullPointerException;

    /**
     * Informacion del usaurio por ID
     * @param idUser Parametro de entrada
     * @return UserListDTO
     * @throws NullPointerException Error
     */
    UserListDTO findByIdUser(Long idUser) throws NullPointerException;

}
