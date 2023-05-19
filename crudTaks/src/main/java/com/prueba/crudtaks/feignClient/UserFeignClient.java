package com.prueba.crudtaks.feignClient;


import com.prueba.crudtaks.feignClient.model.UserDtoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user-ms", url = "http://localhost:8080")
@RequestMapping("/user")
public interface UserFeignClient {

    @GetMapping("/{userId}")
    UserDtoModel findByIdUser(@PathVariable("userId") Long userId) throws NullPointerException;
}
