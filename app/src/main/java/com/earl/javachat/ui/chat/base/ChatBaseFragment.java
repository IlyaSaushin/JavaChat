package com.earl.javachat.ui.chat.base;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.earl.javachat.JavaChatApp;
import com.earl.javachat.R;
import com.earl.javachat.core.Keys;
import com.earl.javachat.core.OnBackPressedListener;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.databinding.FragmentBaseChatBinding;
import com.earl.javachat.ui.NavigationContract;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.firestore.auth.User;

import java.util.Objects;

import javax.inject.Inject;

public class ChatBaseFragment extends Fragment implements OperationResultListener, OnBackPressedListener {

    FragmentBaseChatBinding binding;
    SharedPreferenceManager preferenceManager;
    NavigationContract navigator;
    @Inject
    ChatBaseFragmentPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectBaseChatFragment(this);
        navigator = ((NavigationContract) requireActivity());
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
        fetchUserInfo();
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
                if (userImageBytes == null) {
                    tab.setIcon(R.mipmap.ic_test);
                } else {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(userImageBytes, 0, userImageBytes.length);
                    new ImageView(context).setImageBitmap(bitmap);
                    Icon icon = Icon.createWithBitmap(bitmap);
                    Drawable dr = icon.loadDrawable(context);
                    View imageView = new ImageView(context);
//                tab.setIcon(dr);
                    tab.setIcon(R.mipmap.ic_test);
                }
            }
        }).attach();
    }

    private void fetchUserInfo() {
        navigator.showProgressBar();
        String token = preferenceManager.getString(Keys.KEY_TOKEN);
        if (token == null) {
            Toast.makeText(requireContext(), "No personal token, please reload app", Toast.LENGTH_SHORT).show();
        } else {
            presenter.fetchUserInfo(token, this);
        }
    }

    @Override
    public <T> void success(T success) {
        UserInfo userInfo = (UserInfo) success;
        preferenceManager.putString(Keys.KEY_IMAGE, userInfo.pic);
        preferenceManager.putString(Keys.KEY_EMAIL, userInfo.email);
        preferenceManager.putString(Keys.KEY_NAME, userInfo.username);
        preferenceManager.putString(Keys.KEY_USER_BIO, userInfo.bio);
        navigator.hideProgressBar();
    }

    @Override
    public void fail(Exception exception) {
        navigator.hideProgressBar();
        Toast.makeText(requireContext(), "Unable to get user info, please check server connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        navigator.closeApp();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
