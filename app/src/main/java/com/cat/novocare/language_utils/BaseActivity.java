package com.cat.novocare.language_utils;

import android.content.Context;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;

import static com.cat.novocare.language_utils.LanguageUtils.getLanguage;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.changeLanguage(base, getLanguage(base)));
    }

    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        super.applyOverrideConfiguration(getBaseContext().getResources().getConfiguration());
    }
}
