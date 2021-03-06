package com.example.discogsMusicCollection.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discogsMusicCollection.discogsManager.retrofit.ControllerDiscogsAPI;
import com.example.discogsMusicCollection.CryptoManager;
import com.example.discogsMusicCollection.userInterface.adapterRecycledViewSearch;
import com.example.discogsMusicCollection.R;
import com.example.discogsMusicCollection.UserProfileParameters;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.crypto.NoSuchPaddingException;

public class SignUpActivity extends AppCompatActivity {

    static final String TAG = "SignUpActivity";

    adapterRecycledViewSearch adapterRecycledViewSearch;

    private RecyclerView.LayoutManager layoutManager;

    RecyclerView recyclerView;
    ControllerDiscogsAPI controllerDiscogsAPI = new ControllerDiscogsAPI();

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

            CryptoManager cryptoManager = new CryptoManager();
            try {
                String userInformationFileName = newUser.getUserEmail() + ".txt";
                FileOutputStream userInformationFileOutput = openFileOutput(userInformationFileName.toLowerCase(Locale.ROOT), Context.MODE_PRIVATE);
                cryptoManager.encrypt(newUser,userInformationFileOutput);
            }
            catch(IOException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }

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
