package com.magednan.forsale.ui.frags;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.magednan.forsale.R;
import com.magednan.forsale.databinding.FragmentAdDetailsBinding;
import com.magednan.forsale.models.AdPost;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AdDetailsFragment extends Fragment {
    private FragmentAdDetailsBinding binding;
    private AdDetailsFragmentArgs args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_ad_details, container, false);
        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null){
            args=AdDetailsFragmentArgs.fromBundle(getArguments());
            AdPost post=args.getPost();
            binding.adDetailsTvTitle.setText(post.getTitle());
            binding.adDetailsTvCategory.setText(post.getCategory());
            binding.adDetailsTvCity.setText(post.getCity());
            binding.adDetailsTvDescription.setText(post.getDescription());
            binding.adDetailsTvEmail.setText(post.getEmail());
            String price=String.valueOf(post.getPrice()) ;
            binding.adDetailsTvPrice.setText(price);
            Glide.with(binding.adDetailsIvImage).load(post.getImageUrl())
                    .centerCrop().into(binding.adDetailsIvImage);
        }
    }
}