package com.magednan.forsale.viewmodels;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.Query;
import com.magednan.forsale.models.AdPost;
import com.magednan.forsale.repository.AllAdsRepository;

import java.util.List;

public class AllAdsViewModel extends ViewModel implements AllAdsRepository.OnFirestoreTaskComplete {


    private MutableLiveData<List<AdPost>> postListModelData = new MutableLiveData<>();

    public LiveData<List<AdPost>> getAdsListModelData() {
        return postListModelData;
    }


    private AllAdsRepository firebaseRepository = new AllAdsRepository(this);

    @ViewModelInject
    public AllAdsViewModel() {
        firebaseRepository.getAllAdsData();
    }

    @Override
    public void postsListDataAdded(List<AdPost> postsList) {
postListModelData.postValue(postsList);
        Log.d("TAG", "onComplete VM: "+postsList.toString());
    }


    @Override
    public void onError(Exception e) {

    }
}
