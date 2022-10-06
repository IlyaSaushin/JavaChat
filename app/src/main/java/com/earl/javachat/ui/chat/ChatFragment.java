package com.earl.javachat.ui.chat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.earl.javachat.JavaChatApp;
import com.earl.javachat.core.Keys;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.databinding.FragmentChatBinding;
import com.earl.javachat.ui.NavigationContract;

import javax.inject.Inject;

public class ChatFragment extends Fragment {

    FragmentChatBinding binding;
    NavigationContract navigator;
    SharedPreferenceManager preferenceManager;
    @Inject
    ChatPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectChatFragment(this);
        super.onCreate(savedInstanceState);
        navigator = (NavigationContract) requireContext();
        preferenceManager = new SharedPreferenceManager(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUsersAvatar();
        binding.logOutBtn.setOnClickListener(v -> signOut());
    }

    private void setUsersAvatar() {
        byte[] userImageBytes = Base64.decode(preferenceManager.getString(Keys.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(userImageBytes, 0 , userImageBytes.length);
        binding.userAvatar.setImageBitmap(bitmap);
    }

    private void signOut() {
        presenter.signOut();
        preferenceManager.putBoolean(Keys.KEY_IS_SIGNED_UP, false);
        navigator.login();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
