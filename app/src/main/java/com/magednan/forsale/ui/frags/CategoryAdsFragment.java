package com.magednan.forsale.ui.frags;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.magednan.forsale.R;
import com.magednan.forsale.adapters.AdAdapter;
import com.magednan.forsale.databinding.FragmentCategoryAdsBinding;
import com.magednan.forsale.models.AdPost;
import com.magednan.forsale.viewmodels.CategoryAdsViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CategoryAdsFragment extends Fragment {

    private FragmentCategoryAdsBinding binding;
    private AdAdapter adapter;
    private CategoryAdsFragmentArgs args;
    private String category;
    private CategoryAdsViewModel viewModel;


    public static CategoryAdsFragment newInstance() {
        return new CategoryAdsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_ads, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            args = CategoryAdsFragmentArgs.fromBundle(getArguments());
            category = args.getCategory();
            Toast.makeText(getContext(), category, Toast.LENGTH_SHORT).show();
        }
        viewModel=new ViewModelProvider(getActivity()).get(CategoryAdsViewModel.class);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {

        adapter = new AdAdapter();
        binding.categoryAdsFragRv.setHasFixedSize(true);
        binding.categoryAdsFragRv.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getCategoryAdsData(category);
        viewModel.getAdsListModelData().observe(getViewLifecycleOwner(), new Observer<List<AdPost>>() {
            @Override
            public void onChanged(List<AdPost> adPosts) {
                if(adPosts.size() ==0){
                    showErrorLayout();
                } else {
                    adapter.setPostList(adPosts);
                }
            }
        });
        binding.categoryAdsFragRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AdPost item) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("post",item);
                Navigation.findNavController(binding.categoryAdsFragRv).
                        navigate(R.id.action_categoryAdsFragment_to_adDetailsFragment,bundle);
            }
        });
    }
    private void showErrorLayout(){
        binding.categoryAdsFragRv.setVisibility(View.GONE);
        binding.errorLayout.setVisibility(View.VISIBLE);
        binding.errorBtnCreateAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_categoryAdsFragment_to_createAdFragment);
            }
        });
        binding.errorBtnAllAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_categoryAdsFragment_to_allAdsFragment);
            }
        });
    }


}