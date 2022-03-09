package com.example.imagegallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.imagegallery.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<ImagesResponse> imagesResponseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getAllImages();
        fragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Toast.makeText(this, "You chose : " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        switch (menuItem.getItemId()) {
            case R.id.about:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void fragment() {

        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.FrameLayout, mainFragment);
        fragmentTransaction.commit();
    }


    public void getAllImages() {

        Call<List<ImagesResponse>> imageResponse = ApiClient.getInterface().getAllImage();

        imageResponse.enqueue(new Callback<List<ImagesResponse>>() {
            @Override
            public void onResponse(Call<List<ImagesResponse>> call, Response<List<ImagesResponse>> response) {

                if (response.isSuccessful()) {
                    String massage = "Request Success";
                    Toast.makeText(MainActivity.this, massage, Toast.LENGTH_SHORT).show();
                } else {
                    String massage = "an error occurred. try again";
                    Toast.makeText(MainActivity.this, "Error With" + massage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ImagesResponse>> call, Throwable t) {
                String massage = t.getLocalizedMessage();
                Log.d("Tag", massage);
                Toast.makeText(MainActivity.this, "Error With" + massage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}