package com.earl.javachat.ui.chat.contacts.addNewContact;

import android.os.Bundle;
import android.util.Log;
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
import com.earl.javachat.core.UsersListFetchingResultListener;
import com.earl.javachat.data.restModels.AddContactDto;
import com.earl.javachat.data.restModels.CurrentUser;
import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.databinding.FragmentAddNewContactBinding;
import com.earl.javachat.ui.NavigationContract;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class AddNewContactFragment extends Fragment implements OperationResultListener, OnUserClickListener {

    FragmentAddNewContactBinding binding;
    @Inject
    AddNewContactPresenter presenter;
    NavigationContract navigator;
    SharedPreferenceManager preferenceManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectAddNewContactFragment(this);
        navigator = (NavigationContract) requireActivity();
        preferenceManager = new SharedPreferenceManager(requireContext());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddNewContactBinding.inflate(inflater, container, false);
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
        navigator.showProgressBar();
        String username = preferenceManager.getString(Keys.KEY_NAME);
        presenter.fetchUsersList(username,this);
    }

    @Override
    public <T> void success(T success) {
        List<UserInfo> contactsList = Collections.emptyList();
        try {
            contactsList = (List<UserInfo>) success;
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Unable to cast server response to list", Toast.LENGTH_SHORT).show();
        }
        if (contactsList == null) {
            Toast.makeText(requireContext(), "No contacts still...", Toast.LENGTH_SHORT).show();
            navigator.hideProgressBar();
        } else {
            recycler(contactsList);
        }
    }

    @Override
    public void fail(Exception exception) {
        navigator.hideProgressBar();
        Toast.makeText(requireContext(), "Unable to fetch contacts for user " + exception, Toast.LENGTH_SHORT).show();
    }

    private void recycler(List<UserInfo> list) {
        UsersListAdapter adapter = new UsersListAdapter(list, this);
        binding.usersRecycler.setAdapter(adapter);
        navigator.hideProgressBar();
    }

    @Override
    public void addUserToContacts(String contactUsername) {
        String username = preferenceManager.getString(Keys.KEY_NAME);
        Log.d("tag", "addUserToContacts: USERNAME -> " + username);
        AddContactDto addContactDto = new AddContactDto(username, contactUsername);
        presenter.addUserToContacts(addContactDto);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
