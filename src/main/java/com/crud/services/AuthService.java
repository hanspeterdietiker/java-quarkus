package com.crud.services;

import com.crud.model.UserModel;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthService {

    public UserModel signUp(UserModel userModel) {
        var user = new UserModel();
        user.username = userModel.username;
        user.password = BcryptUtil.bcryptHash(userModel.password);
        user.role = userModel.role;
        user.status = userModel.status;

        UserModel.persist(user);
        return user;
    }
}
