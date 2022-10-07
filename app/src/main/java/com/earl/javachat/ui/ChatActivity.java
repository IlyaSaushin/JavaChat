package com.earl.javachat.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.earl.javachat.databinding.ActivityChatBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChatActivity extends AppCompatActivity {

    ActivityChatBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewPager();
    }

    private void viewPager() {
        binding.pager.setAdapter(new ChatPagerAdapter(this));
        binding.tabs.setTabIconTint(null);

        new TabLayoutMediator(binding.tabs, binding.pager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Chat");
            } else if (position == 1) {
                tab.setText("Groups");
            } else if (position == 2) {
                tab.setText("Contacts");
            } else if (position == 3) {
                tab.setText("Profile");
            }
        }).attach();
    }
}
