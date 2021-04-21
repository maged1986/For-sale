package com.magednan.forsale.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.magednan.forsale.models.AdPost;
import com.magednan.forsale.repository.CategoryAdsRepository;
import com.magednan.forsale.repository.SearchRepository;

import java.util.List;

public class SearchViewModel extends ViewModel implements SearchRepository.OnFirestoreTaskComplete {
    private MutableLiveData<List<AdPost>> postListModelData = new MutableLiveData<>();

    public LiveData<List<AdPost>> getAdsListModelData() {
        return postListModelData;
    }

    private SearchRepository repository = new SearchRepository(this);
    @ViewModelInject
    public SearchViewModel() {
    }
    public void getCategoryAdsData(String s){repository.getSearchAdsData(s);}


    @Override
    public void postsListDataAdded(List<AdPost> postsList) {
        postListModelData.postValue(postsList);
    }

    @Override
    public void onError(Exception e) {

    }
}
