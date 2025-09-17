package com.crud.services;

import com.crud.dto.user.PutUserRequest;
import com.crud.model.UserModel;
import com.crud.model.enums.UserAccountStatus;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import com.crud.exception.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    public List<UserModel> getAllUsers() {
        return UserModel.listAll();
    }

    public UserModel getUserById(UUID userId) {
        return (UserModel) UserModel.findByIdOptional(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public UserModel updateUser(UUID userId, PutUserRequest userDto) {
        var user = getUserById(userId);

        user.username = userDto.username;
        user.password = BcryptUtil.bcryptHash(userDto.password);
        user.role = userDto.role;

        UserModel.persist(user);
        return user;
    }

    public UserModel deleteUserById(UUID userId) {
        var user = getUserById(userId);

        user.status = UserAccountStatus.Inactive;

        UserModel.persist(user);
        return user;
    }
}
