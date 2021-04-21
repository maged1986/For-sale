package com.magednan.forsale.repository;

import android.content.Context;

import com.magednan.forsale.firebase.FireBaseAuth;

import javax.inject.Inject;

public class AuthRepository {
    private FireBaseAuth manager;

    @Inject
    public AuthRepository(FireBaseAuth manager) {
        this.manager = manager;
    }
    public Boolean createNewUser(String name, String country, String state_province, String city,
                                 String email, String password, Context context
    ){
        return manager.createNewUser(name, country, state_province, city, email, password,context);
    }
    public Boolean logIn(String email, String password, Context context) {
        return manager.logIn(email, password, context);
    }
}
