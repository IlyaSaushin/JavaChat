package com.earl.javachat.ui.chat.rooms.newMessage;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.databinding.ContactMsgRecyclerItemBinding;

import java.util.List;

public class ChooseContactRecyclerAdapter extends RecyclerView.Adapter<ChooseContactRecyclerAdapter.ViewHolder> {

    public final List<UserInfo> list;
    ChooseContactRecyclerAdapter(List<UserInfo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ChooseContactRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactMsgRecyclerItemBinding binding = ContactMsgRecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
        ContactMsgRecyclerItemBinding binding;

        ViewHolder(ContactMsgRecyclerItemBinding binding) {
            super(binding.getRoot());
        }

        void bind(UserInfo item) {
            binding.userName.setText(item.username);
        }
    }
}
