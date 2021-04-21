package com.magednan.forsale.ui.frags;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.Query;
import com.magednan.forsale.R;
import com.magednan.forsale.adapters.AdAdapter;
import com.magednan.forsale.databinding.AllAdsFragmentBinding;
import com.magednan.forsale.models.AdPost;
import com.magednan.forsale.ui.activities.MainActivity;
import com.magednan.forsale.viewmodels.AllAdsViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AllAdsFragment extends Fragment {

private AllAdsFragmentBinding binding;
private AdAdapter adapter;
private AllAdsViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.all_ads_fragment, container, false);
        setUpRecyclerView();
        return binding.getRoot();


    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setUpRecyclerView() {
        binding.allAdsFragRv.setHasFixedSize(true);
        binding.allAdsFragRv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdAdapter();
        viewModel=new ViewModelProvider(getActivity()).get(AllAdsViewModel.class);
        viewModel.getAdsListModelData().observe(getActivity(), new Observer<List<AdPost>>() {
            @Override
            public void onChanged(List<AdPost> adPosts) {
                adapter.setPostList(adPosts);
            }
        });
        binding.allAdsFragRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AdPost item) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("post",item);
                Navigation.findNavController(binding.allAdsFragRv).
                        navigate(R.id.action_allAdsFragment_to_adDetailsFragment,bundle);
            }
        });
    }
}