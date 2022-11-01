package com.earl.javachat.ui.chat.contacts.addNewContact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.earl.javachat.JavaChatApp;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.core.UsersListFetchingResultListener;
import com.earl.javachat.data.restModels.CurrentUser;
import com.earl.javachat.databinding.FragmentAddNewContactBinding;
import com.earl.javachat.ui.NavigationContract;

import java.util.List;

import javax.inject.Inject;

public class AddNewContactFragment extends Fragment implements UsersListFetchingResultListener, OperationResultListener, OnUserClickListener {

    FragmentAddNewContactBinding binding;
    @Inject
    AddNewContactPresenter presenter;
    NavigationContract navigator;
    UsersListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectAddNewContactFragment(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddNewContactBinding.inflate(inflater, container, false);
        navigator = (NavigationContract) requireActivity();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchUsersList();
        binding.backStrlka.setOnClickListener(v -> navigator.back());
        binding.clearSearchEd.setOnClickListener(v -> binding.searchEd.setText(""));
    }

    private void fetchUsersList() {
        binding.progressBar.setVisibility(View.VISIBLE);
        presenter.fetchUsersList(this);
    }

    @Override
    public void successList(List<CurrentUser.UserDetails> users) {
        adapter = new UsersListAdapter(users, this);
        binding.usersRecycler.setAdapter(adapter);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void failList(Exception exception) {

    }

    @Override
    public void success() {
        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail(Exception exception) {
        Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addUserToContacts(String userId) {
        presenter.addUserToContacts(userId, this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
