package com.earl.javachat.ui.chat.contacts.addNewContact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.earl.javachat.R;
import com.earl.javachat.data.restModels.CurrentUser;
import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.databinding.GlobalUsersRecyclerItemBinding;

import java.util.List;

interface OnUserClickListener {

    void addUserToContacts(String userId);
}

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserViewHolder> {

    private final List<UserInfo> usersList;
    OnUserClickListener clickListener;

    public UsersListAdapter (List<UserInfo> list, OnUserClickListener clickListener) {
        this.usersList = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GlobalUsersRecyclerItemBinding binding = GlobalUsersRecyclerItemBinding.bind(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.global_users_recycler_item, parent, false));
        return new UserViewHolder(binding, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserInfo user = usersList.get(position);
        holder.bind(user);
        holder.addUserToContacts(user.username);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder implements OnUserClickListener {

        GlobalUsersRecyclerItemBinding binding;
        OnUserClickListener clickListener;

        UserViewHolder(GlobalUsersRecyclerItemBinding binding, OnUserClickListener clickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.clickListener = clickListener;
        }

        void bind(UserInfo user) {
            binding.userName.setText(user.username);
        }

        @Override
        public void addUserToContacts(String username) {
            binding.addUserBtn.setOnClickListener(v -> clickListener.addUserToContacts(username));
            binding.addUserBtn.setVisibility(View.INVISIBLE);
            binding.addUserAdded.setVisibility(View.VISIBLE);
        }
    }
}


