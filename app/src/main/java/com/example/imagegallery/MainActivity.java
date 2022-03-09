package com.example.imagegallery;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.imagegallery.fragment.MainFragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ImagesResponse> imagesResponseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


}