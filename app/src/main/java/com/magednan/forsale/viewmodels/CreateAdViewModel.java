package com.magednan.forsale.viewmodels;

import android.content.Context;
import android.net.Uri;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import com.magednan.forsale.models.AdPost;
import com.magednan.forsale.repository.MainRepository;

public class CreateAdViewModel extends ViewModel {
    private MainRepository repository;

    @ViewModelInject
    public CreateAdViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public void uploadAd(AdPost post, Context context) {
        repository.uploadAd(post, context);
    }

    public String uploadPhoto(Uri uri, Context context){return  repository.uploadPhoto(uri,context);}


}