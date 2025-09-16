package com.crud.services;

import com.crud.model.UserModel;
import jakarta.enterprise.context.ApplicationScoped;
import com.crud.exception.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    public UserModel createUser (UserModel userModel){
       UserModel.persist(userModel);
       return userModel;
    }
    public List<UserModel> getAllUsers() {
        UserModel.findAll();
        return UserModel.listAll();
    }
    public UserModel getUserById(UUID userId) {
        return (UserModel) UserModel.findByIdOptional(userId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
