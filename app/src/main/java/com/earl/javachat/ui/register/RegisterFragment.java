package com.earl.javachat.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.earl.javachat.JavaChatApp;
import com.earl.javachat.core.SuccessOperationResultListener;
import com.earl.javachat.data.models.CurrentUser;
import com.earl.javachat.databinding.FragmentRegisterBinding;
import com.earl.javachat.ui.NavigationContract;

import javax.inject.Inject;

public class RegisterFragment extends Fragment implements SuccessOperationResultListener {

    FragmentRegisterBinding binding;
    @Inject
    RegisterPresenter presenter;
    @Inject
    RegisterFormValidation validation;
    NavigationContract navigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectRegisterFragment(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        navigator = (NavigationContract) requireActivity();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.createAccButton.setOnClickListener(v -> register());
    }

    private Boolean isValidate() {
        return validation.validate(
                binding.emailEd,
                binding.passwordEd,
                binding.passwordTwiceEd
        );
    }

    @Override
    public void fail(Exception exception) {
        // todo
    }

    @Override
    public void success() {
        Toast.makeText(requireContext(), "Done", Toast.LENGTH_LONG).show();
        navigator.hideProgressBar();
        navigator.showAddDetailsFragment();
    }

    private void register() {
        if(isValidate()) {
            navigator.showProgressBar();
            CurrentUser.BaseCurrentUser user = new CurrentUser.BaseCurrentUser(
                    binding.emailEd.getText().toString().trim(),
                    binding.passwordEd.getText().toString().trim()
            );
            presenter.register(user, this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
