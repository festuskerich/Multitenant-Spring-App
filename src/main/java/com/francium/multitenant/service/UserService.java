package com.francium.multitenant.service;

import com.francium.multitenant.entity.UserEntity;
import reactor.core.publisher.Flux;

public interface UserService {
    Flux<UserEntity> getUser();
}
