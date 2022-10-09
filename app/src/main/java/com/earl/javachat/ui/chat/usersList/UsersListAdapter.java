package com.earl.javachat.ui.chat.usersList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.earl.javachat.R;
import com.earl.javachat.data.models.CurrentUser;
import com.earl.javachat.databinding.MessageRecyclerItemBinding;

import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserViewHolder> {

    private final List<CurrentUser.UserDetails> usersList;

    public UsersListAdapter (List<CurrentUser.UserDetails> list) {
        this.usersList = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageRecyclerItemBinding binding = MessageRecyclerItemBinding.bind(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_recycler_item, parent, false));
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        CurrentUser.UserDetails user = usersList.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        MessageRecyclerItemBinding binding;

        UserViewHolder(MessageRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CurrentUser.UserDetails user) {
            binding.userLastMessage.setText("Hello!");
            binding.userName.setText(user.nickName);
        }
    }
}


