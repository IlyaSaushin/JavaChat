package com.earl.javachat.ui.logIn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.earl.javachat.core.Keys;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.core.SuccessOperationResultListener;
import com.earl.javachat.data.models.CurrentUser;
import com.earl.javachat.databinding.FragmentLoginBinding;
import com.earl.javachat.ui.NavigationContract;

import javax.inject.Inject;

public class LogInFragment extends Fragment implements SuccessOperationResultListener {

    FragmentLoginBinding binding;
    NavigationContract navigator;
    SharedPreferenceManager preferenceManager;
    @Inject
    LogInFormValidation validation;
    @Inject
    LogInPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator = ((NavigationContract) requireActivity());
        preferenceManager = new SharedPreferenceManager(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.logInButton.setOnClickListener(v -> logIn());
        binding.signUpButton.setOnClickListener(v -> navigator.register());
    }

    private Boolean isValidate() {
        return validation.validate(
                binding.logInEmail,
                binding.logInPassword
        );
    }

    private void logIn() {
        navigator.showProgressBar();
        if (isValidate()) {
            navigator.showProgressBar();
            CurrentUser.BaseCurrentUser user = new CurrentUser.BaseCurrentUser(
                    binding.logInEmail.getText().toString().trim(),
                    binding.logInPassword.getText().toString().trim()
            );
            presenter.logIn(user, this);
        }
    }

    @Override
    public void success() {
        preferenceManager.putBoolean(Keys.KEY_IS_SIGNED_UP, true);
        navigator.hideProgressBar();
        navigator.chat();
    }

    @Override
    public void fail(Exception exception) {
        // todo
        Toast.makeText(requireContext(), "Error" + exception, Toast.LENGTH_SHORT).show();
        Log.d("tag", "fail: " + exception);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
