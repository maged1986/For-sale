package com.magednan.forsale.ui.frags;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magednan.forsale.R;
import com.magednan.forsale.adapters.CategoriesAdapter;
import com.magednan.forsale.databinding.CategoriesFragmentBinding;
import com.magednan.forsale.models.Categories;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment {

    private CategoriesFragmentBinding binding;
    private CategoriesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.categories_fragment, container, false);
        return binding.getRoot();    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Categories> categoriesList = new ArrayList<Categories>();
        categoriesList.add(new Categories("Cars", R.drawable.ic_car));
        categoriesList.add(new Categories("Houses", R.drawable.ic_house));
        categoriesList.add(new Categories("Cell Phones", R.drawable.ic_mobile));
        categoriesList.add(new Categories("Electronics", R.drawable.ic_tv));
        categoriesList.add(new Categories("Furniture", R.drawable.ic_bed));
        categoriesList.add(new Categories("Jobs", R.drawable.ic_jobs));
        categoriesList.add(new Categories("Services", R.drawable.ic_baseline_medical_services_24));
        categoriesList.add(new Categories("Hobbies", R.drawable.ic_sports));
        categoriesList.add(new Categories("Pets", R.drawable.ic_pets));


        adapter = new CategoriesAdapter(getContext(), categoriesList);
        // Attach the adapter to a ListView
        binding.categoriesFragLv.setAdapter(adapter);
    }

}