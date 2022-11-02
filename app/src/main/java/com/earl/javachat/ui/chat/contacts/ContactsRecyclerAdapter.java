package com.earl.javachat.ui.chat.contacts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.databinding.ContactsRecyclerItemBinding;

import java.util.List;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ViewHolder> {

    List<UserInfo> list;

    ContactsRecyclerAdapter(List<UserInfo> contactsList) {
        this.list = contactsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactsRecyclerItemBinding binding = ContactsRecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserInfo item = list.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ContactsRecyclerItemBinding binding;

        ViewHolder(ContactsRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(UserInfo user) {
            binding.userName.setText(user.username);
        }
    }
}
