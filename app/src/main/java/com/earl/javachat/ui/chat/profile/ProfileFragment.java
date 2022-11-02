package com.earl.javachat.ui.chat.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.earl.javachat.JavaChatApp;
import com.earl.javachat.core.Keys;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.databinding.FragmentProfileBinding;
import com.earl.javachat.ui.NavigationContract;

import javax.inject.Inject;

public class ProfileFragment extends Fragment implements OperationResultListener {

    FragmentProfileBinding binding;
    NavigationContract navigator;
    SharedPreferenceManager preferenceManager;
    @Inject
    ProfilePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectProfileFragment(this);
        navigator = (NavigationContract) requireActivity();
        preferenceManager = new SharedPreferenceManager(requireContext());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.test.setOnClickListener(v -> logOut());
    }

    private void logOut() {
        String token = preferenceManager.getString(Keys.KEY_TOKEN);
        presenter.logOut(token, this);
        preferenceManager.clear();
        navigator.login();
    }

    @Override
    public <T> void success(T success) {
        preferenceManager.clear();
        navigator.login();
    }

    @Override
    public void fail(Exception exception) {
        Toast.makeText(requireContext(), "Failed " + exception, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
