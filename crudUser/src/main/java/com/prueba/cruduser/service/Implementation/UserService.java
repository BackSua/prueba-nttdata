package com.prueba.cruduser.service.Implementation;

import com.prueba.cruduser.dto.ResponseDTO;
import com.prueba.cruduser.dto.UserDTO;
import com.prueba.cruduser.dto.UserListDTO;
import com.prueba.cruduser.entity.UserEntity;
import com.prueba.cruduser.repository.UserRepository;
import com.prueba.cruduser.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public ResponseDTO saveUser(UserDTO userDTO) throws NullPointerException {

        if (Objects.isNull(userDTO)) {
            throw new NullPointerException("Parametro de entrada no puede llegar nulo");
        }

        String pass = Base64.getEncoder().encodeToString(userDTO.getPassword().getBytes());

        ModelMapper mapper = new ModelMapper();
        UserEntity user = mapper.map(userDTO, UserEntity.class);
        user.setPassword(pass);
        user.setRegistrationDate(new Date());

        userRepository.save(user);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("User registrado con exito");

        return responseDTO;
    }

    @Override
    public ResponseDTO updateUser(Long idUser, UserDTO userDTO) throws NullPointerException {

        if (Objects.isNull(userDTO) || idUser == null) {
            throw new NullPointerException("Parametros de entrada no pueden ser nulos");
        }

        UserEntity user = userRepository.findById(idUser).orElseThrow(
                () -> new NullPointerException("User no encontrado o no existe.")
        );

        String pass = Base64.getEncoder().encodeToString(userDTO.getPassword().getBytes());

        ModelMapper mapper = new ModelMapper();
        UserEntity userUpdate = mapper.map(user, UserEntity.class);
        userUpdate.setUserName(userDTO.getUserName());
        userUpdate.setName(userDTO.getName());
        userUpdate.setLastName(userDTO.getLastName());
        userUpdate.setEmail(userDTO.getEmail());
        userUpdate.setPassword(pass);

        userRepository.save(userUpdate);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("User actualizado con exito");

        return responseDTO;

    }

    @Override
    public ResponseDTO deleteUser(Long idUser) throws NullPointerException {
        if (idUser == null) {
            throw new NullPointerException("Parametro de entrada no puede ser nulo");
        }

        userRepository.deleteById(idUser);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("User eliminado con exito");

        return responseDTO;

    }

    @Override
    public List<UserListDTO> listUsers() throws NullPointerException {

        List<UserEntity> listUsers = userRepository.findAll();

        if (listUsers.isEmpty()) {
            throw new NullPointerException("Error al consultar todos los usuaios.");
        }

        ModelMapper mapper = new ModelMapper();

        List<UserListDTO> userListDTOS = listUsers.stream().map(
                userEntity -> {
                    UserListDTO userListDTO = mapper.map(userEntity, UserListDTO.class);
                    return userListDTO;
                }).collect(Collectors.toList());

        return userListDTOS;
    }

    @Override
    public UserListDTO findByIdUser(Long idUser) throws NullPointerException {

        if (idUser == null){
            throw new NullPointerException("Los parametros de entrada no pueden ser nulos");
        }

        UserEntity user = userRepository.findById(idUser).orElseThrow(
                ()-> new NullPointerException("El id de usuario no ha sido encontrado o no existe")
        );

        ModelMapper mapper = new ModelMapper();
        UserListDTO userDTO = mapper.map(user, UserListDTO.class);

        return userDTO;

    }
}
