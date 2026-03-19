package com.crud.services;

import com.crud.model.UserModel;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AuthServiceTest {

    @Inject
    AuthService authService;

    @Test
    void testSignUp() {
        PanacheMock.mock(UserModel.class);
        var userModel = new UserModel();
        userModel.username = "testuser";
        userModel.password = "testpassword";


        var createdUser = authService.signUp(userModel);

        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.username);
        assertNotEquals("testpassword", createdUser.password);

        PanacheMock.verify(UserModel.class).persist(Mockito.any(UserModel.class), Mockito.any());


    }

    @Test
    @Transactional
    void testLogin() {

        var userModel = new UserModel();
        userModel.username = "testuser";
        userModel.password = "testpassword";

        authService.signUp(userModel);

        String token = authService.login("testuser", "testpassword");
        assertNotNull(token);
        assertFalse(token.isEmpty());



    }
}