package com.magednan.forsale.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.magednan.forsale.models.AdPost;
import com.magednan.forsale.repository.CategoryAdsRepository;
import com.magednan.forsale.repository.MyAdsRepository;

import java.util.List;

public class CategoryAdsViewModel extends ViewModel implements CategoryAdsRepository.OnFirestoreTaskComplete {
    private MutableLiveData<List<AdPost>> postListModelData = new MutableLiveData<>();

    public LiveData<List<AdPost>> getAdsListModelData() {
        return postListModelData;
    }

    private CategoryAdsRepository repository = new CategoryAdsRepository(this);
@ViewModelInject
    public CategoryAdsViewModel() {
    }
    public void getCategoryAdsData(String category){repository.getCategoryAdsData(category);}


    @Override
    public void postsListDataAdded(List<AdPost> postsList) {
        postListModelData.postValue(postsList);
    }

    @Override
    public void onError(Exception e) {

    }
}
