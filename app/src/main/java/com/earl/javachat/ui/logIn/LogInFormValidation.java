package com.earl.javachat.ui.logIn;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;

import com.earl.javachat.R;

public interface LogInFormValidation {

    boolean validate(EditText email, EditText password);

    class BaseValidation implements LogInFormValidation {

        @Override
        public boolean validate(EditText email, EditText password) {
            boolean validation = true;
            Context context = email.getContext();
            if (email.getText().toString().trim().isEmpty()) {
                email.setError(context.getString(R.string.empty_string_err));
                validation = false;
            } else if (password.getText().toString().trim().isEmpty()) {
                password.setError(context.getString(R.string.empty_string_err));
                validation = false;
            } else if (password.getText().toString().length() < 6) {
                password.setError(context.getString(R.string.shor_pass_err));
                validation = false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
                email.setError(context.getString(R.string.incorrect_email_err));
                validation = false;
            }
            return validation;
        }
    }
}
