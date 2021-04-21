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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magednan.forsale.R;
import com.magednan.forsale.adapters.AdAdapter;
import com.magednan.forsale.databinding.FragmentMyAdsBinding;
import com.magednan.forsale.models.AdPost;
import com.magednan.forsale.viewmodels.MyAdsViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyAdsFragment extends Fragment {
    private FragmentMyAdsBinding binding;
    private AdAdapter adapter;
    private MyAdsViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_ads, container, false);
        setUpRecyclerView();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setUpRecyclerView() {
        binding.myAdsFragRv.setHasFixedSize(true);
        binding.myAdsFragRv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdAdapter();
        viewModel = new ViewModelProvider(getActivity()).get(MyAdsViewModel.class);
        viewModel.getAdsListModelData().observe(getActivity(), new Observer<List<AdPost>>() {
            @Override
            public void onChanged(List<AdPost> adPosts) {
                if (adPosts.size() == 0) {
                    showErrorLayout();
                } else {
                    adapter.setPostList(adPosts);
                }
            }
        });
        binding.myAdsFragRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AdPost item) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("post", item);
                Navigation.findNavController(binding.myAdsFragRv).
                        navigate(R.id.action_myAdsFragment_to_adDetailsFragment, bundle);
            }
        });
    }

    private void showErrorLayout() {
        binding.myAdsFragRv.setVisibility(View.GONE);
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

