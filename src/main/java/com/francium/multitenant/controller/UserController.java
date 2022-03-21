package com.francium.multitenant.controller;

import com.francium.multitenant.entity.UserEntity;
import com.francium.multitenant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    final UserService userService;
    @GetMapping("/")
    ResponseEntity<Flux<UserEntity>> get() {
        return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
    }

}
