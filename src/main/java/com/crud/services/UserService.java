package com.crud.services;

import com.crud.model.UserModel;
import com.crud.model.enums.UserAccountStatus;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import com.crud.exception.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    public UserModel createUser(UserModel userModel) {
        var user = new UserModel();
        user.username = userModel.username;
        user.password = BcryptUtil.bcryptHash(userModel.password);
        user.status = UserAccountStatus.Active;

        UserModel.persist(user);
        return user;
    }

    public List<UserModel> getAllUsers() {
        return UserModel.listAll();
    }

    public UserModel getUserById(UUID userId) {
        return (UserModel) UserModel.findByIdOptional(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public UserModel updateUser(UUID userId, UserModel userModel) {
        var user = getUserById(userId);

        user.username = userModel.username;
        user.password = BcryptUtil.bcryptHash(userModel.password);

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
