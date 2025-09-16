package com.crud.model;


import com.crud.model.enums.UserCountStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class UserModel extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID userId;

    public String username;

    public String password;

    public UserCountStatus status;
}
