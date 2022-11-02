package com.earl.javachat.ui.chat.rooms;

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
import com.earl.javachat.data.restModels.RoomResponseDto;
import com.earl.javachat.databinding.FragmentRoomsBinding;
import com.earl.javachat.ui.NavigationContract;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class RoomsFragment extends Fragment implements OperationResultListener {

    FragmentRoomsBinding binding;
    SharedPreferenceManager preferenceManager;
    @Inject
    RoomsPresenter presenter;
    NavigationContract navigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectChatFragment(this);
        navigator = ((NavigationContract) requireActivity());
        super.onCreate(savedInstanceState);
        preferenceManager = new SharedPreferenceManager(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRoomsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchRoomsForUser();
    }

    private void fetchRoomsForUser() {
        navigator.showProgressBar();
        String token = preferenceManager.getString(Keys.KEY_TOKEN);
        presenter.fetchRoomsForUser(token, this);
    }

    @Override
    public <T> void success(T success) {
        List<RoomResponseDto> roomsList = Collections.emptyList();
        try {
            roomsList = (List<RoomResponseDto>) success;
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Unable to cast server response to list", Toast.LENGTH_SHORT).show();
        }
        if (roomsList == null) {
            Toast.makeText(requireContext(), "No chats still...", Toast.LENGTH_SHORT).show();
            navigator.hideProgressBar();
        } else {
            recycler(roomsList);
        }
    }

    private void recycler(List<RoomResponseDto> roomsList) {
        RoomsRecyclerAdapter adapter = new RoomsRecyclerAdapter(roomsList);
        binding.recycler.setAdapter(adapter);
        navigator.hideProgressBar();
    }

    @Override
    public void fail(Exception exception) {
        navigator.hideProgressBar();
        Toast.makeText(requireContext(), "Unable to fetch rooms for user " + exception, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
