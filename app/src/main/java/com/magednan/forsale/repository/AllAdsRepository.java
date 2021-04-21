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

public class AllAdsRepository {

    private OnFirestoreTaskComplete onFirestoreTaskComplete;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private Query AdsRef = firebaseFirestore.collection("Ads");

    @Inject
    public AllAdsRepository(OnFirestoreTaskComplete onFirestoreTaskComplete) {
        this.onFirestoreTaskComplete = onFirestoreTaskComplete;
    }


    public void getAllAdsData() {
        AdsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<AdPost> posts=task.getResult().toObjects(AdPost.class);
                    onFirestoreTaskComplete.postsListDataAdded(posts);
                    Log.d("TAG", "onComplete: "+task.getResult().getDocuments().toString());
                } else {
                    onFirestoreTaskComplete.onError(task.getException());
                }
            }
        });
    }


    public interface OnFirestoreTaskComplete {
        void postsListDataAdded(List<AdPost> postsList);
        void onError(Exception e);
    }

}
