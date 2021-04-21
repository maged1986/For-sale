package com.magednan.forsale.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.magednan.forsale.R;
import com.magednan.forsale.models.AdPost;

import java.util.ArrayList;
import java.util.List;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.AdHolder> {
    public List<AdPost> postList = new ArrayList<>();
    private  OnItemClickListener listener;

    public AdAdapter() {
    }

    @NonNull
    @Override
    public AdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item,
                parent, false);
        return new AdAdapter.AdHolder(v);
    }
    public void setPostList(List<AdPost> postList){
        this.postList=postList;
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(@NonNull AdHolder holder, int position) {
        AdPost model = postList.get(position);

        Glide.with(holder.imageView).load(model.getImageUrl()).centerCrop().into(holder.imageView);
        holder.textViewTitle.setText(model.getTitle());
        String price = String.valueOf(model.getPrice());
        holder.textViewPrice.setText(price);
        holder.textViewProvince.setText(model.getState_province());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(model);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (postList != null) {
            return postList.size();
        }
        return 0;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public class AdHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewPrice;
        TextView textViewProvince;
        ImageView imageView;
        CardView parent;

        public AdHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.ad_item_tv_title);
            textViewPrice = itemView.findViewById(R.id.ad_item_tv_price);
            textViewProvince = itemView.findViewById(R.id.ad_item_tv_state_province);
            imageView = itemView.findViewById(R.id.ad_item_iv_image);
            parent = itemView.findViewById(R.id.ad_item_cv_parent);

        }

    }
    public interface OnItemClickListener {
        void onItemClick(AdPost item);
    }




}
