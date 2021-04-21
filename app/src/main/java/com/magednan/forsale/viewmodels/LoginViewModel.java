package com.magednan.forsale.viewmodels;

import android.content.Context;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import com.magednan.forsale.repository.AuthRepository;

public class LoginViewModel extends ViewModel {
    private AuthRepository repository;
    @ViewModelInject
    public LoginViewModel(AuthRepository repository) {
        this.repository = repository;
    }
    public Boolean logIn(String email, String password, Context context) {
        return   repository.logIn(email, password, context);
    }
}