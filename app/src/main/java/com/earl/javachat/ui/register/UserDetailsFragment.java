package com.earl.javachat.ui.register;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.earl.javachat.JavaChatApp;
import com.earl.javachat.core.Keys;
import com.earl.javachat.core.SharedPreferenceManager;
import com.earl.javachat.core.SuccessOperationResultListener;
import com.earl.javachat.databinding.FragmentUserDetailsBinding;
import com.earl.javachat.ui.NavigationContract;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import javax.inject.Inject;

public class UserDetailsFragment extends Fragment implements SuccessOperationResultListener {

    FragmentUserDetailsBinding binding;
    String encodedImage;
    NavigationContract navigator;
    SharedPreferenceManager preferenceManager;
    @Inject
    RegisterPresenter presenter;
    @Inject
    UserDetailsFormValidation validation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((JavaChatApp) requireContext().getApplicationContext()).appComponent.injectUserDetailsFragment(this);
        navigator = (NavigationContract) requireActivity();
        preferenceManager = new SharedPreferenceManager(requireContext());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addAvatarTv.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });
        binding.addDetailsButton.setOnClickListener(v -> saveAccountDetails());
    }

    private boolean isValidate() {
        return validation.validate(
                encodedImage,
                binding.nameEd,
                binding.nickEd,
                binding.userBio
        );
    }

    private void saveAccountDetails() {
        if (isValidate()) {

            navigator.showProgressBar();

            preferenceManager.putBoolean(Keys.KEY_IS_SIGNED_UP, true);
            preferenceManager.putString(Keys.KEY_IMAGE, encodedImage);
            preferenceManager.putString(Keys.KEY_NAME, binding.nameEd.getText().toString().trim());
            preferenceManager.putString(Keys.KEY_NICK_NAME, binding.nickEd.getText().toString().trim());
            preferenceManager.putString(Keys.KEY_USER_BIO, binding.userBio.getText().toString().trim());

            HashMap<String, Object> userDetails = new HashMap<>();
            userDetails.put(Keys.KEY_IMAGE, encodedImage);
            userDetails.put(Keys.KEY_NAME, binding.nameEd.getText().toString().trim());
            userDetails.put(Keys.KEY_NICK_NAME, binding.nickEd.getText().toString().trim());
            userDetails.put(Keys.KEY_USER_BIO, binding.userBio.getText().toString().trim());

            presenter.saveAccountDetails(userDetails, this);
        }
    }

    @Override
    public void success() {
        navigator.hideProgressBar();
        Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show();
        navigator.chat();
    }

    @Override
    public void fail(Exception exception) {
        // todo
    }

    private String encodeImage(Bitmap bitmap) {
        int previewWidth = 150;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = requireContext().getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            binding.accAvatar.setImageBitmap(bitmap);
                            binding.addAvatarTv.setVisibility(View.GONE);
                            encodedImage = encodeImage(bitmap);
                        } catch (FileNotFoundException exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
