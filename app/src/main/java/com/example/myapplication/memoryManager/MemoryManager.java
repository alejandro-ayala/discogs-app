package com.example.myapplication.memoryManager;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class MemoryManager extends AppCompatActivity {
    private static final String TAG = "Controller";

    public void saveUserData(boolean memType) {
        Log.d(TAG, "saveFavouriteRelease: ");
        FileOutputStream fos;

        try {  // Añadir al fichero
            if (memType) {
                fos = openFileOutput("fichero.txt", Context.MODE_APPEND); // Memoria interna
            } else {    // Comprobar estado SD card
                String estadoTarjetaSD = Environment.getExternalStorageState();
                if (estadoTarjetaSD.equals(Environment.MEDIA_MOUNTED)) {
                    String rutaFich = getExternalFilesDir(null) + "/" + "fichero.txt";
                    fos = new FileOutputStream(rutaFich, true);
                } else {
                    /*Toast.makeText(
                            this,
                            getString(R.string.txtErrorMemExterna),
                            Toast.LENGTH_SHORT
                    ).show();*/
                    return;
                }
            }

            fos.write("persistent memory".getBytes(StandardCharsets.UTF_8));
            fos.write('\n');
            fos.close();
        } catch (Exception e) {
            Log.e(TAG, "FILE I/O ERROR: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void saveFavouriteRelease(String title, String urlCover) {


        FavouriteMusicEntity music = new FavouriteMusicEntity("nirvana","sdasd");
        favouriteMusicViewModel.insert(music);
    }
}
