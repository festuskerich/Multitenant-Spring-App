package com.francium.multitenant.service;

import com.francium.multitenant.entity.UserEntity;
import com.francium.multitenant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;

    @Override
    public Flux<UserEntity> getUser() {
        return userRepository.findAll();
    }
}
