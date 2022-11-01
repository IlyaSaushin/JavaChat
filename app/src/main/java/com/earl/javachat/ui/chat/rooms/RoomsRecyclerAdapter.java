package com.earl.javachat.ui.chat.rooms;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.earl.javachat.data.restModels.RoomResponseDto;
import com.earl.javachat.databinding.RoomRecyclerItemBinding;

import java.util.List;

public class RoomsRecyclerAdapter extends RecyclerView.Adapter<RoomsRecyclerAdapter.ViewHolder> {

    public final List<RoomResponseDto> list;

    RoomsRecyclerAdapter(List<RoomResponseDto> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoomRecyclerItemBinding binding = RoomRecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoomResponseDto room = list.get(position);
        holder.bind(room);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
            RoomRecyclerItemBinding binding;

            ViewHolder(RoomRecyclerItemBinding binding) {
                super(binding.getRoot());
            }

            void bind(RoomResponseDto room) {
                binding.userName.setText(room.name);
            }
    }
}