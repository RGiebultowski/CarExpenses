package com.example.wydatkisamochodowe.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.wydatkisamochodowe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        getSupportActionBar().hide();
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.setSelectedItemId(R.id.home);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = fragmentSwitcher(menuItem);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                return true;
            }
        });
    }

    private Fragment fragmentSwitcher(MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.addCar:
                fragment = new AddCarFragment();
                break;
            case R.id.home:
                fragment = new HomeFragment();
                break;
            case R.id.tankUp:
                fragment = new TankUpFragment();
                break;
        }
        return fragment;
    }

    private static int backButtonCount = 0;

    @Override
    public void onBackPressed() {
        new CountDownTimer(4000, 1000){
            public void onTick(long millisUntilFinished){
                backButtonCount = 1;
            }
            public void onFinish(){
                backButtonCount = 0;
            }
        }.start();
        if (backButtonCount >= 1){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Wciśnij jescze raz aby zamknąć aplikację.", Toast.LENGTH_LONG).show();
            backButtonCount++;
        }
    }
}