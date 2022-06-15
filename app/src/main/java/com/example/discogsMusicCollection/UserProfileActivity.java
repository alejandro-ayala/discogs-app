package com.example.discogsMusicCollection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile_activity);

        Intent intent = getIntent();

        try {
            CryptoManager cryptoManager = new CryptoManager();
            FileInputStream userInformationFileInput = openFileInput(SignUpActivity.USER_INFORMATION_FILENAME);
            UserProfileParameters decryptNewUser = (UserProfileParameters) cryptoManager.decrypt(userInformationFileInput);
            Log.d(TAG, "New User decrypt.");

            TextView userName = (TextView) findViewById(R.id.etName);
            TextView userLastName = (TextView) findViewById(R.id.etLastName);
            TextView userPhone = (TextView) findViewById(R.id.etPhone);
            TextView userDirection = (TextView) findViewById(R.id.etUserDirection);

            userName.setText(decryptNewUser.getUserName());
            userLastName.setText(decryptNewUser.getUserLastName());
            userPhone.setText(decryptNewUser.getUserPhone());
            userDirection.setText(decryptNewUser.getUserDirection());

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

        //userName.setText();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
