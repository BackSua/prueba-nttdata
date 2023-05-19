package com.prueba.cruduser.controller;

import com.prueba.cruduser.dto.ResponseDTO;
import com.prueba.cruduser.dto.UserDTO;
import com.prueba.cruduser.dto.UserListDTO;
import com.prueba.cruduser.service.Implementation.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void saveUser() {

        UserDTO userDTO = Mockito.mock(UserDTO.class);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("OK");

        Mockito.when(userService.saveUser(userDTO))
                .thenReturn(responseDTO);

        var result = userController.saveUser(userDTO);

        Assertions.assertNotNull(result);

    }

    @Test
    void updateUser() {

        Long idUser = 123L;
        UserDTO userDTO = Mockito.mock(UserDTO.class);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("OK");

        Mockito.when(userService.updateUser(idUser, userDTO))
                .thenReturn(responseDTO);

        var result = userController.updateUser(idUser, userDTO);

        Assertions.assertNotNull(result);

    }

    @Test
    void deleteUser() {

        Long idUser = 123L;

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("OK");

        Mockito.when(userService.deleteUser(idUser))
                .thenReturn(responseDTO);

        var result = userController.deleteUser(idUser);

        Assertions.assertNotNull(result);

    }

    @Test
    void listUsers() {

        UserListDTO userListDTO = new UserListDTO();

        List<UserListDTO> userListDTOS = new ArrayList<>();
        userListDTOS.add(userListDTO);

        Mockito.when(userService.listUsers())
                .thenReturn(userListDTOS);

        var result = userController.listUsers();

        Assertions.assertNotNull(result);

    }

    @Test
    void findByIdUser() {
        Long idUser = 123L;

        UserListDTO userListDTO = Mockito.mock(UserListDTO.class);

        Mockito.when(userService.findByIdUser(idUser)).thenReturn(userListDTO);

        var result = userController.findByIdUser(idUser);

        Assertions.assertNotNull(result);
    }
}