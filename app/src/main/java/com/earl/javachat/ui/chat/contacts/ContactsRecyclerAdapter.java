package com.earl.javachat.ui.chat.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.databinding.ContactsRecyclerItemBinding;

import java.util.List;

interface OnUserClickListener {

    void removeUserFromContacts(String contactUsername);
}

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ViewHolder> {

    List<UserInfo> list;
    OnUserClickListener clickListener;

    ContactsRecyclerAdapter(List<UserInfo> contactsList, OnUserClickListener clickListener) {
        this.list = contactsList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactsRecyclerItemBinding binding = ContactsRecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserInfo item = list.get(position);
        holder.bind(item);
        holder.removeUserFromContacts(item.username);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements OnUserClickListener {

        ContactsRecyclerItemBinding binding;
        OnUserClickListener clickListener;

        ViewHolder(ContactsRecyclerItemBinding binding, OnUserClickListener clickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.clickListener = clickListener;
        }

        void bind(UserInfo user) {
            binding.userName.setText(user.username);
        }

        @Override
        public void removeUserFromContacts(String contactUsername) {
            binding.deleteProfile.setOnClickListener(view -> {
                clickListener.removeUserFromContacts(contactUsername);
            });
        }
    }
}
