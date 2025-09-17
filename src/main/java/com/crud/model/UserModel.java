package com.crud.model;


import com.crud.model.enums.UserAccountStatus;
import com.crud.model.enums.UserRole;
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

    public UserAccountStatus status;

    public UserRole role;

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UserAccountStatus getStatus() {
        return status;
    }

    public void setStatus(UserAccountStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserModel() {
        this.status = UserAccountStatus.active;
        this.role = UserRole.user;
    }

    public static UserModel findByUsername(String username) {
        return find("username", username).firstResult();
    }
}
