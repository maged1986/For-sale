package com.magednan.forsale.viewmodels;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.magednan.forsale.models.AdPost;
import com.magednan.forsale.repository.AllAdsRepository;
import com.magednan.forsale.repository.MyAdsRepository;

import java.util.List;

public class MyAdsViewModel extends ViewModel implements MyAdsRepository.OnFirestoreTaskComplete {

    private MutableLiveData<List<AdPost>> postListModelData = new MutableLiveData<>();

    public LiveData<List<AdPost>> getAdsListModelData() {
        return postListModelData;
    }

    private MyAdsRepository firebaseRepository = new MyAdsRepository(this);

    @ViewModelInject
    public MyAdsViewModel() {
        firebaseRepository.getMyAdsData();
    }

    @Override
    public void postsListDataAdded(List<AdPost> postsList) {
        postListModelData.postValue(postsList);
        Log.d("TAG", "onComplete my VM: " + postsList.toString());

    }

    @Override
    public void onError(Exception e) {

    }
}
