package com.magednan.forsale.ui.frags;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.magednan.forsale.R;
import com.magednan.forsale.databinding.RegisterFragmentBinding;
import com.magednan.forsale.ui.activities.MainActivity;
import com.magednan.forsale.viewmodels.RegisterViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    private RegisterFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        binding.registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (doStringsMatch(binding.registerEtPassword, binding.registerEtConfirmPassword)) {
                    if ((checkField(binding.registerEtEmail) == true
                            && checkField(binding.registerEtCountry) == true
                            && checkField(binding.registerEtStateProvince) == true
                            && checkField(binding.registerEtCity) == true
                            && checkField(binding.registerEtName) == true
                            && checkField(binding.registerEtPassword) == true
                            && checkField(binding.registerEtConfirmPassword) == true)
                    ) {
                        if( mViewModel.createNewUser(binding.registerEtName.getText().toString()
                                , binding.registerEtCountry.getText().toString()
                                , binding.registerEtStateProvince.getText().toString()
                                , binding.registerEtCity.getText().toString()
                                , binding.registerEtEmail.getText().toString()
                                , binding.registerEtPassword.getText().toString(),
                                getContext()) ==true){
                            startActivity(new Intent(getActivity(), MainActivity.class));
                        }else {
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            Toast.makeText(getContext(), "this is required ", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        Toast.makeText(getContext(), "this is required field", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getContext(), "paswords doesnmatch", Toast.LENGTH_SHORT).show();
                }
                resetFields();
            }
        });

        binding.registerTvLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.loginFragment);
            }
        });

    }


    private boolean doStringsMatch(EditText editText1, EditText editText2) {
        return editText1.getText().toString().equals(editText2.getText().toString());
    }

    private void resetFields() {
        binding.registerEtCountry.setText(null);
        binding.registerEtStateProvince.setText(null);
        binding.registerEtCity.setText(null);
        binding.registerEtEmail.setText(null);
        binding.registerEtName.setText(null);
        binding.registerEtPassword.setText(null);
        binding.registerEtConfirmPassword.setText(null);
    }

    private Boolean checkField(EditText editText) {
        if (editText.getText().toString().length() > 2) {
            return true;
        } else {
            editText.setError("this is reqired field");
            Toast.makeText(getContext(),"please fill all required fields",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}