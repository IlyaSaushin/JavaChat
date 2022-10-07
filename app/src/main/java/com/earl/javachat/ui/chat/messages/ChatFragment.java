package com.earl.javachat.ui.chat.messages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.earl.javachat.JavaChatApp;
import com.earl.javachat.core.Keys;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.core.ListFetchingListener;
import com.earl.javachat.data.models.CurrentUser;
import com.earl.javachat.databinding.FragmentChatBinding;
import com.earl.javachat.ui.MainActivity;
import com.earl.javachat.ui.chat.usersList.UsersListAdapter;

import java.util.List;

import javax.inject.Inject;

public class ChatFragment extends Fragment implements ListFetchingListener {

    FragmentChatBinding binding;
    SharedPreferenceManager preferenceManager;
    @Inject
    ChatPresenter presenter;

    UsersListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectChatFragment(this);
        super.onCreate(savedInstanceState);
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
        fetchUsersList();
        setUsersAvatar();
        binding.logOutBtn.setOnClickListener(v -> signOut());
    }

    private void fetchUsersList() {
        binding.progressBar.setVisibility(View.VISIBLE);
        presenter.fetchUsersList(this);
    }

    private void setUsersAvatar() {
        byte[] userImageBytes = Base64.decode(preferenceManager.getString(Keys.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(userImageBytes, 0 , userImageBytes.length);
        binding.userAvatar.setImageBitmap(bitmap);
    }

    private void signOut() {
        presenter.signOut();
        preferenceManager.putBoolean(Keys.KEY_IS_SIGNED_UP, false);
        Intent intent = new Intent(requireContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void success(List<CurrentUser.UserDetails> users) {
        binding.progressBar.setVisibility(View.GONE);
        adapter = new UsersListAdapter(users);
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void fail(Exception exception) {
        Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}