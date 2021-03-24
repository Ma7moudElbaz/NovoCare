package com.cat.novocare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.cat.novocare.main_activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends LocalizationActivity {
    // Splash screen timer
    final int SPLASH_TIME_OUT = 500;

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
            LanguageEnit.initLanguage(getBaseContext());
            setLanguage("ar");
            navigateLogin();
        });

        enBtn.setOnClickListener(v -> {
            LanguageEnit.initLanguage(getBaseContext());
            setLanguage("en");
            navigateLogin();
        });

        if (LanguageEnit.getLanguageInit(getBaseContext())) {
            navigateLoginTimer();
        } else {
            choose_lang_cont.setVisibility(View.VISIBLE);
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