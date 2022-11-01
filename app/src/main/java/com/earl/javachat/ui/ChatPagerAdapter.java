package com.earl.javachat.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.earl.javachat.ui.chat.rooms.ChatFragment;
import com.earl.javachat.ui.chat.contacts.ContactsFragment;
import com.earl.javachat.ui.chat.groups.GroupsFragment;
import com.earl.javachat.ui.chat.profile.ProfileFragment;

public class ChatPagerAdapter extends FragmentStateAdapter {

    public ChatPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new Fragment();
        if (position == 0) {
            fragment = new ChatFragment();
        } else if (position == 1) {
            fragment = new GroupsFragment();
        } else if (position == 2) {
            fragment = new ContactsFragment();
        } else if (position == 3) {
            fragment = new ProfileFragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
