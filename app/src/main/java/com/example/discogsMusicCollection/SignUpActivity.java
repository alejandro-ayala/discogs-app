package com.example.discogsMusicCollection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    static final String TAG = "SignUpActivity";

    MyAdapterRecycledView myAdapterRecycledView;

    private RecyclerView.LayoutManager layoutManager;

    RecyclerView recyclerView;
    Controller controller = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        Intent intent = getIntent();

    }

    public void finish(View view) {
        // Do something in response to button click
        Log.d(TAG, "finish signUp!!");
        EditText etEmail = (EditText)findViewById(R.id.etEmail);
        EditText etPass = (EditText)findViewById(R.id.etPassword);
        EditText etPass2 = (EditText)findViewById(R.id.etPasswordRepeated);
        EditText etNameSignUp = (EditText)findViewById(R.id.etNameSignUp);
        EditText etLastNameSignUp = (EditText)findViewById(R.id.etLastNameSignUp);
        EditText etPhoneSignUp = (EditText)findViewById(R.id.etPhoneSignUp);
        EditText etUserDirectionSignUp = (EditText)findViewById(R.id.etUserDirectionSignUp);

        String newEmail = etEmail.getText().toString();
        String newPass = etPass.getText().toString();
        String newPassRepeated = etPass2.getText().toString();

        String newUserName = etNameSignUp.getText().toString();
        String newUserLastName = etLastNameSignUp.getText().toString();
        String newUserPhone = etPhoneSignUp.getText().toString();
        String newUserDirection = etUserDirectionSignUp.getText().toString();
        UserProfileParameters newUser = new UserProfileParameters(newEmail, newPass, newUserName, newUserLastName, newUserPhone, newUserDirection);

        if(checkSignUpRequirements(newUser,newPassRepeated )) {
            // Create EmailAuthCredential with email and password
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            //AuthCredential newUserCredential = EmailAuthProvider.getCredential(newUser.getUserEmail(), newUser.getUserPassword());
            mAuth.createUserWithEmailAndPassword(newUser.getUserEmail(),newUser.getUserPassword());
            Log.d(TAG, "New User signUp.");
            Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else  {
            Log.d(TAG, "Error signUp!!");
        }
    }
    private boolean checkSignUpRequirements(UserProfileParameters newUser, String newPassRepeated) {
        if((newUser.getUserPassword().equals(newPassRepeated)) && !newUser.getUserEmail().isEmpty()) {
            return true;
        }
        else {
            return false;
        }

    }
}
