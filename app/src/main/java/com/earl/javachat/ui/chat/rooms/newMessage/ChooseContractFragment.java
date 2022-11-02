package com.earl.javachat.ui.chat.rooms.newMessage;

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
import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.databinding.FragmentChooseContactBinding;
import com.earl.javachat.ui.NavigationContract;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class ChooseContractFragment extends Fragment implements OperationResultListener {

    private FragmentChooseContactBinding binding;
    NavigationContract navigator;
    SharedPreferenceManager preferenceManager;
    @Inject
    ChooseContactPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectChooseContactFragment(this);
        navigator = ((NavigationContract) requireActivity());
        super.onCreate(savedInstanceState);
        preferenceManager = new SharedPreferenceManager(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChooseContactBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchContactsList();
    }

    private void fetchContactsList() {
        navigator.showProgressBar();
        String token = preferenceManager.getString(Keys.KEY_TOKEN);
        presenter.fetchContacts(token, this);
    }

    private void recycler(List<UserInfo> list) {
        ChooseContactRecyclerAdapter adapter = new ChooseContactRecyclerAdapter(list);
        binding.usersRecycler.setAdapter(adapter);
        navigator.hideProgressBar();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
