package com.cat.novocare;

import androidx.annotation.NonNull;
import com.akexorcist.localizationactivity.ui.LocalizationApplication;
import java.util.Locale;

public class NovoCare extends LocalizationApplication {

    @NonNull
    @Override
    public Locale getDefaultLanguage() {
        return Locale.ENGLISH;
    }
}
