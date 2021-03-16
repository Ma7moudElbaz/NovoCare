package com.cat.novocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cat.novocare.main_activity.MainActivity;
import com.cat.novocare.language_utils.LocaleHelper;

import java.util.Timer;
import java.util.TimerTask;

import static com.cat.novocare.language_utils.LanguageUtils.getLanguage;

public class SplashActivity extends AppCompatActivity {
    // Splash screen timer
    int SPLASH_TIME_OUT = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LocaleHelper.changeLanguage(getBaseContext(), getLanguage(getBaseContext()));
        navigateLogin();
    }

    private void navigateLogin() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}