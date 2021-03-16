package com.cat.novocare;

import android.app.Application;
import android.content.Context;

import com.cat.novocare.language_utils.LocaleHelper;

import static com.cat.novocare.language_utils.LanguageUtils.getLanguage;

public class NovoCare extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.changeLanguage(base, getLanguage(base)));
    }

}
