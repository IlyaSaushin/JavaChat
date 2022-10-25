package com.earl.javachat.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.earl.javachat.R;
import com.earl.javachat.core.Keys;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.databinding.FragmentBaseChatBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class ChatBaseFragment extends Fragment {

    FragmentBaseChatBinding binding;
    SharedPreferenceManager preferenceManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBaseChatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceManager = new SharedPreferenceManager(requireContext());
        viewPager(requireContext());
    }

    private void viewPager(Context context) {
        binding.pager.setAdapter(new ChatPagerAdapter(requireActivity()));

        TabLayout tabs = binding.tabs;
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(context, R.color.secondPrimaryBlue);
                Objects.requireNonNull(tab.getIcon()).setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(context, R.color.tabs);
                Objects.requireNonNull(tab.getIcon()).setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        new TabLayoutMediator(binding.tabs, binding.pager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Chats");
                tab.setIcon(R.drawable.chat_icon);
            } else if (position == 1) {
                tab.setText("Groups");
                tab.setIcon(R.drawable.groups_icon);
            } else if (position == 2) {
                tab.setText("Contacts");
                tab.setIcon(R.drawable.contacts_icon);
            } else if (position == 3) {
                tab.setText("Profile");
                byte[] userImageBytes = Base64.decode(preferenceManager.getString(Keys.KEY_IMAGE), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(userImageBytes, 0 , userImageBytes.length);
                new ImageView(context).setImageBitmap(bitmap);
                Icon icon = Icon.createWithBitmap(bitmap);
                Drawable dr = icon.loadDrawable(context);
                View imageView = new ImageView(context);
//                tab.setIcon(dr);
                tab.setIcon(R.mipmap.ic_test);
            }
        }).attach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
