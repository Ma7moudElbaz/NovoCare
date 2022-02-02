package com.cat.novocare.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import com.akexorcist.localizationactivity.ui.LocalizationApplication;
import java.util.Locale;

public class App extends LocalizationApplication {

    @NonNull
    @Override
    public Locale getDefaultLanguage(@NonNull Context context) {
        return Locale.ENGLISH;
    }
}
