package com.magednan.forsale.ui.frags;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.magednan.forsale.R;
import com.magednan.forsale.adapters.AdAdapter;
import com.magednan.forsale.databinding.SearchFragmentBinding;
import com.magednan.forsale.models.AdPost;
import com.magednan.forsale.viewmodels.SearchViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends Fragment {
    private SearchFragmentBinding binding;
    private AdAdapter adapter;
    private SearchFragmentArgs args;
    private String searchItem;
    private SearchViewModel viewModel;


    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel=new ViewModelProvider(getActivity()).get(SearchViewModel.class);
        if (getArguments() != null) {
            args = SearchFragmentArgs.fromBundle(getArguments());
             searchItem = args.getSearchItem();
             viewModel.getCategoryAdsData(searchItem);
            Toast.makeText(getContext(),searchItem,Toast.LENGTH_LONG).show();
        }
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        adapter = new AdAdapter();
        binding.searchFragRv.setHasFixedSize(true);
        binding.searchFragRv.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getAdsListModelData().observe(getViewLifecycleOwner(), new Observer<List<AdPost>>() {
            @Override
            public void onChanged(List<AdPost> adPosts) {
                adapter.setPostList(adPosts);
            }
        });
        binding.searchFragRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AdPost item) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("post",item);
                Navigation.findNavController(binding.searchFragRv).
                        navigate(R.id.action_searchFragment_to_adDetailsFragment,bundle);
            }
        });

    }
}