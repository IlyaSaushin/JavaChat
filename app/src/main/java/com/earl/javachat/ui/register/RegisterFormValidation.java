package com.earl.javachat.ui.register;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;

import com.earl.javachat.R;

public interface RegisterFormValidation {

    boolean validate(
        EditText email,
        EditText firstPassword,
        EditText secondPassword
    );

    class Base implements RegisterFormValidation {

        @Override
        public boolean validate(EditText email, EditText firstPassword, EditText secondPassword) {

            boolean validate = true;
            Context context = email.getContext();

            if (email.getText().toString().trim().isEmpty()) {
                email.setError(context.getString(R.string.empty_string_err));
                validate = false;
            }  else if (firstPassword.getText().toString().trim().isEmpty()) {
                firstPassword.setError(context.getString(R.string.empty_string_err));
                validate = false;
            } else if (secondPassword.getText().toString().trim().isEmpty()) {
                secondPassword.setError(context.getString(R.string.empty_string_err));
                validate = false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
                email.setError(context.getString(R.string.incorrect_email_err));
                validate = false;
            } else if (firstPassword.getText().toString().trim().length() <= 6) {
                firstPassword.setError(context.getString(R.string.shor_pass_err));
                validate = false;
            } else if (!firstPassword.getText().toString().trim().equals(secondPassword.getText().toString().trim())) {
                secondPassword.setError(context.getString(R.string.unequal_pass_err));
                validate = false;
            }
            return validate;
        }
    }
}
