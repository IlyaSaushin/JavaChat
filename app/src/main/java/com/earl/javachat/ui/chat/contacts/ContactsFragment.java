package com.earl.javachat.ui.chat.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.earl.javachat.R;
import com.earl.javachat.databinding.FragmentContactsBinding;
import com.earl.javachat.ui.NavigationContract;

public class ContactsFragment extends Fragment {

    FragmentContactsBinding binding;
    NavigationContract navigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
//        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectContactsFragment(this);
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
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        binding.inviteFriend.setOnClickListener(v -> navigator.showAddNewContactFragment());
    }

    private void fetchContactsList() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
