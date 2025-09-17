package com.crud.services;

import com.crud.exception.AuthException.CredentialsException;
import com.crud.model.UserModel;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AuthService {


    private final JwtService jwtService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public UserModel signUp(UserModel userModel) {
        var user = new UserModel();
        user.username = userModel.username;
        user.password = BcryptUtil.bcryptHash(userModel.password);
        user.role = userModel.role;
        user.status = userModel.status;

        UserModel.persist(user);
        return user;
    }

    public String login(String username, String password) {
        UserModel user = UserModel.findByUsername(username);
        if  (user != null && BcryptUtil.matches(password, user.password)) {

            return jwtService.generateJwt(user.username, user.role);
        } else {
            throw new CredentialsException("Invalid username or password");
        }


    }

}

