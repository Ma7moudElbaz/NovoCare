package com.cat.novocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cat.novocare.main_activity.MainActivity;
import com.cat.novocare.language_utils.LocaleHelper;

import java.util.Timer;
import java.util.TimerTask;

import static com.cat.novocare.language_utils.LanguageUtils.getLanguage;

public class SplashActivity extends AppCompatActivity {
    // Splash screen timer
    int SPLASH_TIME_OUT = 500;

    LinearLayout choose_lang_cont;
    Button arBtn, enBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        choose_lang_cont = findViewById(R.id.choose_lang_cont);

        arBtn = findViewById(R.id.ar_btn);
        enBtn = findViewById(R.id.en_btn);

        arBtn.setOnClickListener(v -> {
            LocaleHelper.changeLanguage(getBaseContext(), "ar");
            navigateLogin();
        });

        enBtn.setOnClickListener(v -> {
            LocaleHelper.changeLanguage(getBaseContext(), "en");
            navigateLogin();
        });

        if (getLanguage(getBaseContext()).equals("sys")) {
            choose_lang_cont.setVisibility(View.VISIBLE);
        } else {
            LocaleHelper.changeLanguage(SplashActivity.this, getLanguage(getBaseContext()));
            navigateLoginTimer();
        }

    }

    private void navigateLoginTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void navigateLogin() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

}