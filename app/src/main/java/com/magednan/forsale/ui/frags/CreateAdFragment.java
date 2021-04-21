package com.magednan.forsale.ui.frags;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.magednan.forsale.R;
import com.magednan.forsale.databinding.CreateAdFragmentBinding;
import com.magednan.forsale.models.AdPost;
import com.magednan.forsale.viewmodels.CreateAdViewModel;

import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CreateAdFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final int PICKFILE_REQUEST_CODE = 1234;
    private CreateAdViewModel mViewModel;
    private String category;
    private final String postId = "FirebaseDatabase";
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser userId = auth.getCurrentUser();
    private CreateAdFragmentBinding binding;
    private Uri selectedImageUri;
    private String imageUrl ;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.create_ad_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        category = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CreateAdViewModel.class);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner
                , android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(this);
        binding.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICKFILE_REQUEST_CODE);
            }
        });
        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "uploadPost: " + userId.getUid());
                float price = Float.parseFloat(binding.inputPrice.getText().toString());
                AdPost post = new AdPost(
                        userId.getUid(), mViewModel.uploadPhoto(selectedImageUri, requireContext()),
                        binding.inputTitle.getText().toString(), category,
                        binding.inputDescription.getText().toString(), price,
                        binding.inputCountry.getText().toString(),
                        binding.inputStateProvince.getText().toString(),
                        binding.inputCity.getText().toString(),
                        binding.inputEmail.getText().toString());
                mViewModel.uploadAd(post,requireContext());
              //  resetFields();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data.getData();
            Glide.with(getContext()).load(selectedImageUri).into(binding.postImage);
            mViewModel.uploadPhoto(selectedImageUri, requireContext());
            imageUrl = mViewModel.uploadPhoto(selectedImageUri, requireContext());
        }
    }

    public void resetFields() {
        binding.postImage.setImageResource(R.drawable.ic_launcher_background);
        binding.inputTitle.setText(null);
        binding.inputDescription.setText(null);
        binding.inputPrice.setText(null);
        binding.inputCountry.setText(null);
        binding.inputStateProvince.setText(null);
        binding.inputCity.setText(null);
        binding.inputEmail.setText(null);
    }

    public Boolean checkField(EditText editText) {
        if (editText.getText().toString().length() > 2) {
            return true;
        } else {
            editText.setError("this is reqired field");
            Toast.makeText(getContext(), "please fill all required fields", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}