package com.cat.novocare;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate;

import java.util.Locale;

import io.customerly.Customerly;

public class NovoCare extends Application {
    final LocalizationApplicationDelegate localizationDelegate = new LocalizationApplicationDelegate();

    @Override
    protected void attachBaseContext(Context base) {

        localizationDelegate.setDefaultLanguage(base, Locale.ENGLISH);
        super.attachBaseContext(localizationDelegate.attachBaseContext(base));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localizationDelegate.onConfigurationChanged(this);
    }

    @Override
    public Context getApplicationContext() {
        return localizationDelegate.getApplicationContext(super.getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Customerly.configure(this, "6284d5e0");
    }
}
