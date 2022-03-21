package com.francium.multitenant.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("customer")
public class UserEntity {
    @Id
    @Column("id")
    Long id;
    String username;
}
