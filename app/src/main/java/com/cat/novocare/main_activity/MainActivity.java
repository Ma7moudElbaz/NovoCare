package com.cat.novocare.main_activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.cat.novocare.R;
import com.cat.novocare.language_utils.BaseActivity;
import com.cat.novocare.language_utils.LanguageUtils;
import com.cat.novocare.main_activity.edu_center.EduCenterFragment;
import com.cat.novocare.main_activity.home.HomeFragment;
import com.cat.novocare.main_activity.locator.LocatorFragment;
import com.cat.novocare.main_activity.more.MoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    public void setContentFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentLayout, fragment);
        fragmentTransaction.commit();
    }

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.e("TAG", Locale.getDefault().toString());
        Log.e("TAG", LanguageUtils.getLanguage(this));

        setContentFragment(new HomeFragment());

        bottomNavigationView = findViewById(R.id.btm_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_home) {
            setContentFragment(new HomeFragment());
        } else if (id == R.id.navigation_locator) {
            setContentFragment(new LocatorFragment());
        } else if (id == R.id.navigation_eduCenter) {
            setContentFragment(new EduCenterFragment());
        } else if (id == R.id.navigation_more) {
            setContentFragment(new MoreFragment());
        }
        return true;
    }
}