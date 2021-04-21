package com.magednan.forsale.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;

import com.magednan.forsale.R;
import com.magednan.forsale.models.Categories;

import java.util.ArrayList;

import javax.inject.Inject;

public class CategoriesAdapter  extends ArrayAdapter<Categories> {
    ArrayList<Categories> list=new ArrayList<Categories>();

    @Inject
    public CategoriesAdapter(@NonNull Context context, ArrayList<Categories> list) {
        super(context,0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Categories categories=getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.categories_item, parent, false);

        TextView tvName = convertView.findViewById(R.id.categories_item_tv_title);
        ImageView ivImage = convertView.findViewById(R.id.categories_item_iv_image);
        CardView cvParent=convertView.findViewById(R.id.categories_item_cv_parent);
        tvName.setText(categories.getName());
        ivImage.setImageResource(categories.getImageId());
        cvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle= new Bundle();
                bundle.putString("category",categories.getName());
               Navigation.findNavController(view).navigate(R.id.action_categoriesFragment_to_categoryAdsFragment,bundle);
                Toast.makeText(getContext(), categories.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
