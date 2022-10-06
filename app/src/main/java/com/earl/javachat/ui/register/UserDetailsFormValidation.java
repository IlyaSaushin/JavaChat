package com.earl.javachat.ui.register;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.earl.javachat.R;

public interface UserDetailsFormValidation {

    Boolean validate(
            String image,
            EditText name,
            EditText nickName,
            EditText bio
    );

    class BaseUserDetailsValidation implements UserDetailsFormValidation {
        @Override
        public Boolean validate(String image, EditText name, EditText nickName, EditText bio) {
            boolean validate = true;
            Context context = name.getContext();
            if (image == null) {
                validate = false;
                Toast.makeText(context, "Choose your profile image, please", Toast.LENGTH_SHORT).show();
            } else if (name.getText().toString().trim().isEmpty()) {
                validate = false;
                name.setError(context.getString(R.string.empty_string_err));
            } else if (nickName.getText().toString().trim().isEmpty()) {
                validate = false;
                nickName.setError(context.getString(R.string.empty_string_err));
            } else if (bio.getText().toString().trim().isEmpty()) {
                validate = false;
                bio.setError(context.getString(R.string.empty_string_err));
            }
            return validate;
        }
    }
}
