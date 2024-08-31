package com.example.ex_5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private Button verifyButton;
    private TextView resultTextView;

    // A simple in-memory store for usernames and passwords
    private HashMap<String, String> userStore = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);
        verifyButton = findViewById(R.id.verifyButton);
        resultTextView = findViewById(R.id.resultTextView);

// Register Button Click Listener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

// Verify Button Click Listener
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            resultTextView.setText("Please enter both username and password.");
            resultTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else if (userStore.containsKey(username)) {
            resultTextView.setText("Username already exists.");
            resultTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            userStore.put(username, password);
            resultTextView.setText("User registered successfully.");
            resultTextView.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }
    }

    private boolean isValidUsername(String username) {
// Username must be at least 6 characters long
        if (username.length() < 6) {
            return false;
        }

// Username must contain both uppercase and lowercase letters
        if (!username.matches(".[a-z].") || !username.matches(".[A-Z].")) {
            return false;
        }

// Username should not contain any special characters
        if (username.matches(".[^a-zA-Z0-9].")) {
            return false;
        }

        return true;
    }


    private void verifyUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (userStore.containsKey(username)) {
            if (userStore.get(username).equals(password)) {
                resultTextView.setText("Login successful.");
                resultTextView.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            } else {
                resultTextView.setText("Incorrect password.");
                resultTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }
        } else {
            resultTextView.setText("User does not exist.");
            resultTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private boolean containsSpecialCharacter(String str) {
// Check if the password contains at least one special character
        return str.matches(".[!@#$%^&()\\-_=+{};:,<.>].*");
}
}
