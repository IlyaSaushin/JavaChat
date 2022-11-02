package com.earl.javachat.ui.chat.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.earl.javachat.JavaChatApp;
import com.earl.javachat.R;
import com.earl.javachat.core.Keys;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.databinding.FragmentContactsBinding;
import com.earl.javachat.ui.NavigationContract;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class ContactsFragment extends Fragment implements OperationResultListener {

    FragmentContactsBinding binding;
    NavigationContract navigator;
    @Inject
    ContactsPresenter presenter;
    SharedPreferenceManager preferenceManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectContactsFragment(this);
        preferenceManager = new SharedPreferenceManager(requireContext());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentContactsBinding.inflate(inflater, container, false);
        navigator = (NavigationContract) requireActivity();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchContactsList();
        binding.inviteFriend.setOnClickListener(v -> navigator.showAddNewContactFragment());
    }

    private void fetchContactsList() {
        navigator.showProgressBar();
        String token = preferenceManager.getString(Keys.KEY_TOKEN);
        presenter.fetchContactsList(token, this);
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
        ContactsRecyclerAdapter adapter = new ContactsRecyclerAdapter(list);
        binding.recycler.setAdapter(adapter);
        navigator.hideProgressBar();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
