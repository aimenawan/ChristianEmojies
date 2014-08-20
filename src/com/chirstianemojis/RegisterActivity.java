package com.chirstianemojis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        listeners();
    }

    private void listeners() {
        Button btnRegister = (Button) findViewById(R.id.raBtnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fullName = (EditText) findViewById(R.id.etFullName);
                EditText userName = (EditText) findViewById(R.id.etUsername);
                EditText password = (EditText) findViewById(R.id.etPassword);
                EditText confirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
                EditText email = (EditText) findViewById(R.id.etEmail);

                if (fullName.getText().length() < 1 || userName.getText().length() < 1) {
                    Utility.simpleAlert("Error", "Please fill all the fields", RegisterActivity.this);
                    return;
                }
                if (password.getText().length() < 5) {
                    Utility.simpleAlert("Error", "Password must be longer than 5 characters", RegisterActivity.this);
                    return;
                }
                if (!password.getText().equals(confirmPassword.getText())) {
                    Utility.simpleAlert("Error", "Passwords don't match", RegisterActivity.this);
                    return;
                }
                Pattern pattern = Pattern.compile("^.+@.+\\..+$");
                Matcher matcher = pattern.matcher(email.getText());
                if (!matcher.find()) {
                    Utility.simpleAlert("Error", "Please enter a valid email", RegisterActivity.this);
                    return;
                }

                startActivity(new Intent(RegisterActivity.this, ProfileActivity.class));

            }
        });
    }

}
