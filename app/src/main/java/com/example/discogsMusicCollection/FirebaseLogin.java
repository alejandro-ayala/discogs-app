package com.example.discogsMusicCollection;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.discogsMusicCollection.activities.MainActivity;
import com.example.discogsMusicCollection.activities.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseLogin extends AppCompatActivity implements View.OnClickListener {

    static final String LOG_TAG = "FirebaseLogin";

    private FirebaseAuth mAuth;

    private EditText mEmailField;
    private EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_login);

        // Fields
        mEmailField = findViewById(R.id.fieldEmail);
        mPasswordField = findViewById(R.id.fieldPassword);

        // Click listeners
        findViewById(R.id.buttonSignIn).setOnClickListener(this);
        findViewById(R.id.buttonSignUp).setOnClickListener(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Check logout request
        boolean logout = false;
        try {
            Intent intent = getIntent();
            logout = intent.getExtras().getBoolean(MainActivity.LOGOUT_REQUEST);
        }  catch(Exception e) { }
        if(logout == true) {
            signOut();
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.buttonSignIn) {
            signInWithCredentials();
        } else if (i == R.id.buttonSignUp) {
            signUp();
        }
    }

    private boolean validateLinkForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError(getString(R.string.field_required));
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError(getString(R.string.field_required));
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void signInWithCredentials() {
        if (!validateLinkForm()) {
            return;
        }

        // Get email and password from form
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        // Create EmailAuthCredential with email and password
        AuthCredential credential = EmailAuthProvider.getCredential(email, password);

        // [START signin_with_email_and_password]
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i(LOG_TAG, "signInWithCredentials:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(LOG_TAG, "signInWithCredentials:failure", task.getException());
                            Toast.makeText(FirebaseLogin.this, "Authentication failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END signin_with_email_and_password]
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void signUp() {
        Log.w(LOG_TAG, "signUp");
        Intent intent = new Intent(FirebaseLogin.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void updateUI(FirebaseUser user) {
        TextView uidView = findViewById(R.id.statusId);
        TextView emailView = findViewById(R.id.statusEmail);

        boolean isSignedIn = (user != null);

        // Status text
        if (isSignedIn) {
            uidView.setText(R.string.signed_in);
            emailView.setText(getString(R.string.email_fmt, user.getEmail()));
            mPasswordField.setText("");
            mEmailField.setText("");
            Log.i(LOG_TAG, "signedIn: " + getString(R.string.id_fmt, user.getDisplayName()));
            Intent intent = new Intent(FirebaseLogin.this,MainActivity.class);
            startActivity(intent);
        } else {
            uidView.setText(R.string.signed_out);
            emailView.setText(null);
            Log.i(LOG_TAG, "signOut: " + getString(R.string.signed_out));
        }

        // Button visibility
        findViewById(R.id.buttonSignIn).setEnabled(!isSignedIn);

    }
}