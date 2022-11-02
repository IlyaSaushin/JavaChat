package com.earl.javachat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.earl.javachat.R;
import com.earl.javachat.core.Keys;
import com.earl.javachat.core.OnBackPressedListener;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.databinding.ActivityMainBinding;
import com.earl.javachat.ui.chat.base.ChatBaseFragment;
import com.earl.javachat.ui.chat.contacts.addNewContact.AddNewContactFragment;
import com.earl.javachat.ui.logIn.LogInFragment;
import com.earl.javachat.ui.register.RegisterFragment;
import com.earl.javachat.ui.register.UserDetailsFragment;

public class MainActivity extends AppCompatActivity implements NavigationContract {

    ActivityMainBinding binding;
    Dialog progressBar;
    SharedPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager = new SharedPreferenceManager(this);
        boolean isSignedUp = preferenceManager.getBoolean(Keys.KEY_IS_SIGNED_UP);
        if (isSignedUp) {
            chat();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new LogInFragment())
                    .commit();
        }
    }

    @Override
    public void login() {
        showFragment(new LogInFragment());
    }

    @Override
    public void register() {
        showFragment(new RegisterFragment());
    }

    @Override
    public void showAddDetailsFragment(String email, String password) {
        showFragment(new UserDetailsFragment(email, password));
    }

    @Override
    public void chat() {
        showFragment(new ChatBaseFragment());
    }

    @Override
    public void showAddNewContactFragment() {
        showFragment(new AddNewContactFragment());
    }

    @Override
    public void showProgressBar() {
        progressBar = new Dialog(this, android.R.style.Theme_Black);
        View view = LayoutInflater.from(this).inflate(R.layout.progress_bar, null);
        progressBar.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressBar.getWindow().setBackgroundDrawableResource(R.color.custom_transparent);
        progressBar.setContentView(view);
        progressBar.show();
    }

    @Override
    public void hideProgressBar() {
        progressBar.dismiss();
    }

    @Override
    public void back() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void closeApp() {
        finish();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        OnBackPressedListener backPressedListener = null;
        for (Fragment fragment: fm.getFragments()) {
            if (fragment instanceof  OnBackPressedListener) {
                backPressedListener = (OnBackPressedListener) fragment;
                break;
            }
        }

        if (backPressedListener != null) {
            backPressedListener.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}