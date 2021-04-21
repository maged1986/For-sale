package com.magednan.forsale.viewmodels;

import android.content.Context;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import com.magednan.forsale.repository.AuthRepository;

public class RegisterViewModel extends ViewModel {
    private AuthRepository repository;

    @ViewModelInject
    public RegisterViewModel(AuthRepository repository) {
        this.repository = repository;
    }

    public Boolean createNewUser(String name, String country, String state_province, String city,
                                 String email, String password, Context context
    ) {
        return   repository.createNewUser(name, country, state_province, city, email, password, context);
    }
}