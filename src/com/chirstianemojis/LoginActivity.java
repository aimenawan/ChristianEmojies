package com.chirstianemojis;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import com.chirstianemojis.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        listeners();


    }

    public void listeners() {
        Button laBtnRegister = (Button) findViewById(R.id.laBtnRegister);
        laBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        Button letsStart = (Button) findViewById(R.id.btnLets_start);
        letsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.etEmail);
                EditText password = (EditText) findViewById(R.id.etPassword);

                if (password.getText().length() < 5) {
                    Utility.simpleAlert("Error", "Password must be longer than 5 characters", LoginActivity.this);
                    return;
                }
                Pattern pattern = Pattern.compile("^.+@.+\\..+$");
                Matcher matcher = pattern.matcher(email.getText());
                if (!matcher.find()) {
                    Utility.simpleAlert("Error", "Please enter a valid email", LoginActivity.this);
                }

            }
        });
    }
}
