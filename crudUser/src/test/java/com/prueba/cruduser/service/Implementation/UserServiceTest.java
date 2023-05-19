package com.prueba.cruduser.service.Implementation;

import com.prueba.cruduser.dto.ResponseDTO;
import com.prueba.cruduser.dto.UserDTO;
import com.prueba.cruduser.dto.UserListDTO;
import com.prueba.cruduser.entity.UserEntity;
import com.prueba.cruduser.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void saveUser() {
        ResponseDTO response = new ResponseDTO();
        response.setResponse("User registrado con exito");

        UserDTO user = new UserDTO();
        user.setEmail("anpch@example.com");
        user.setPassword("123456");
        user.setUserName("Juan");
        user.setName("Juan Dar");
        user.setLastName("Pepito");

        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(new UserEntity());

        var result = userService.saveUser(user);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("User registrado con exito",
                response.getResponse());

        Mockito.verify(userRepository, Mockito.times(1))
                .save(Mockito.any(UserEntity.class));

    }

    @Test
    void updateUser() {

        Long idUser = 123L;

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("anpch@example.com");
        userDTO.setPassword("123456");
        userDTO.setUserName("Juan");
        userDTO.setName("Juan Dar");
        userDTO.setLastName("Pepito");

        UserEntity user = new UserEntity();

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("User actualizado con exito");

        Mockito.when(userRepository.findById(idUser))
                .thenReturn(Optional.of(user));

        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(new UserEntity());

        var result = userService.updateUser(idUser, userDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("User actualizado con exito",
                responseDTO.getResponse());

    }

    @Test
    void deleteUser() {

        Long idUser = 123L;

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("User eliminado con exito");

        var response = userService.deleteUser(idUser);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("User eliminado con exito",
                response.getResponse());

        Mockito.verify(userRepository, Mockito.times(1))
                .deleteById(idUser);
    }

    @Test
    void listUsers() {

        UserEntity user = new UserEntity();

        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(user);

        UserListDTO userListDTO = new UserListDTO();

        List<UserListDTO> userListDTOS = new ArrayList<>();
        userListDTOS.add(userListDTO);

        Mockito.when(userRepository.findAll())
                .thenReturn(userEntities);

        var result = userService.listUsers();

        Assertions.assertNotNull(result);

    }

    @Test
    void findByIdUser() {

        Long idUser = 123L;

        UserEntity user = new UserEntity();

        ModelMapper mapper = new ModelMapper();
        UserListDTO userDTO = mapper.map(user, UserListDTO.class);

        Mockito.when(userRepository.findById(idUser))
                .thenReturn(Optional.of(user));

        var result = userService.findByIdUser(idUser);

        Assertions.assertNotNull(result);

    }
}