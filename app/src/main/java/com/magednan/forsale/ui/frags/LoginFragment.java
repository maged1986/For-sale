package com.magednan.forsale.ui.frags;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.magednan.forsale.R;
import com.magednan.forsale.databinding.LoginFragmentBinding;
import com.magednan.forsale.ui.activities.MainActivity;
import com.magednan.forsale.viewmodels.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private LoginFragmentBinding binding;
    private LoginViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate
                (inflater, R.layout.login_fragment, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkField(binding.loginEtEmail) == true && checkField(binding.loginEtPassword) == true) {
                    if( mViewModel.logIn(binding.loginEtEmail.getText().toString(),
                            binding.loginEtPassword.getText().toString(), getContext())==true){
                        //  startActivity(new Intent(getActivity(), MainActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        Toast.makeText(getContext(), "please fill all required ", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        binding.loginTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.registerFragment);
            }
        });
    }

    private Boolean checkField(EditText editText) {
        if (editText.getText().toString().length() > 2) {
            return true;
        } else {
            editText.setError("this is required field");
            Toast.makeText(getContext(), "please fill all required fields", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}