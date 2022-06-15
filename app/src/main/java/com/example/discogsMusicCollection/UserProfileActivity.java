package com.example.discogsMusicCollection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

public class UserProfileActivity extends AppCompatActivity {

    static final String TAG = "UserProfileActivity";

    MyAdapterRecycledView myAdapterRecycledView;

    private RecyclerView.LayoutManager layoutManager;

    RecyclerView recyclerView;
    Controller controller = new Controller();
    UserProfileParameters userProfile = new UserProfileParameters();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile_activity);

        Intent intent = getIntent();

        try {
            CryptoManager cryptoManager = new CryptoManager();
            FileInputStream userInformationFileInput = openFileInput(SignUpActivity.USER_INFORMATION_FILENAME);
            userProfile = (UserProfileParameters) cryptoManager.decrypt(userInformationFileInput);
            Log.d(TAG, "New User decrypt.");

            TextView userName = (TextView) findViewById(R.id.etName);
            TextView userLastName = (TextView) findViewById(R.id.etLastName);
            TextView userPhone = (TextView) findViewById(R.id.etPhone);
            TextView userDirection = (TextView) findViewById(R.id.etUserDirection);

            userName.setText(userProfile.getUserName());
            userLastName.setText(userProfile.getUserLastName());
            userPhone.setText(userProfile.getUserPhone());
            userDirection.setText(userProfile.getUserDirection());

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

    }

    public void saveProfile(View view) {

        Log.d(TAG, "saveProfile!!");
        // Update user profile, encrypt and store.

        TextView userName = (TextView) findViewById(R.id.etName);
        TextView userLastName = (TextView) findViewById(R.id.etLastName);
        TextView userPhone = (TextView) findViewById(R.id.etPhone);
        TextView userDirection = (TextView) findViewById(R.id.etUserDirection);

        userProfile.setUserName(userName.getText().toString());
        userProfile.setUserLastName(userLastName.getText().toString());
        userProfile.setUserPhone(userPhone.getText().toString());
        userProfile.setUserDirection(userDirection.getText().toString());


        try {
            CryptoManager cryptoManager = new CryptoManager();
            FileOutputStream userInformationFileOutput = openFileOutput(SignUpActivity.USER_INFORMATION_FILENAME, Context.MODE_PRIVATE);
            cryptoManager.encrypt(userProfile,userInformationFileOutput);
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
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
