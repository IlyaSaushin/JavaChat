package com.earl.javachat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import com.earl.javachat.R;
import com.earl.javachat.core.Keys;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.databinding.ActivityMainBinding;
import com.earl.javachat.ui.chat.ChatFragment;
import com.earl.javachat.ui.logIn.LogInFragment;
import com.earl.javachat.ui.register.RegisterFragment;
import com.earl.javachat.ui.register.UserDetailsFragment;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

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
            showFragment(new ChatFragment());
        } else {
            showFragment(new LogInFragment());
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
        showFragment(new ChatFragment());
    }

    @Override
    public void showProgressBar() {
        Dialog pb = new Dialog(this, android.R.style.Theme_Black);
        progressBar = pb;
        View view = LayoutInflater.from(this).inflate(R.layout.progress_bar, null);
        pb.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pb.getWindow().setBackgroundDrawableResource(R.color.custom_transparent);
        pb.setContentView(view);
        pb.show();
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