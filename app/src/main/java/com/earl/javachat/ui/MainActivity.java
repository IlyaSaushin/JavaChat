package com.earl.javachat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.earl.javachat.R;
import com.earl.javachat.core.Keys;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.databinding.ActivityMainBinding;
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
    public void showAddDetailsFragment() {
        showFragment(new UserDetailsFragment());
    }

    @Override
    public void chat() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
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

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}