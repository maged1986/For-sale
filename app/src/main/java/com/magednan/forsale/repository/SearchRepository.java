package com.magednan.forsale.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.magednan.forsale.models.AdPost;

import java.util.List;

import javax.inject.Inject;

public class SearchRepository {
    private OnFirestoreTaskComplete onFirestoreTaskComplete;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


    @Inject
    public SearchRepository(OnFirestoreTaskComplete onFirestoreTaskComplete) {
        this.onFirestoreTaskComplete = onFirestoreTaskComplete;
    }
    public void getSearchAdsData(String s) {
        Query AdsRef = firebaseFirestore.collection("Ads")
                .whereEqualTo("title", s);
        AdsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<AdPost> posts=task.getResult().toObjects(AdPost.class);
                    onFirestoreTaskComplete.postsListDataAdded(posts);
                    Log.d("TAG", "onComplete: my ads "+task.getResult().getDocuments().toString());
                } else {
                    onFirestoreTaskComplete.onError(task.getException());
                    Log.d("TAG", "onError: my ads "+task.getException().getMessage());
                }
            }
        });
    }

    public interface OnFirestoreTaskComplete {
        void postsListDataAdded(List<AdPost> postsList);

        void onError(Exception e);
    }
}

