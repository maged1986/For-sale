package com.magednan.forsale.repository;

import android.content.Context;
import android.net.Uri;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.magednan.forsale.firebase.FirebaseDataManager;
import com.magednan.forsale.models.AdPost;

import javax.inject.Inject;

public class MainRepository {
    private FirebaseDataManager manager;

    @Inject
    public MainRepository(FirebaseDataManager manager) {
        this.manager = manager;
    }
    public void uploadAd(AdPost post, Context context) {
        manager.uploadAd(post,context);
    }
    public String uploadPhoto(Uri uri, Context context){return  manager.uploadPhoto(uri,context);}
}
