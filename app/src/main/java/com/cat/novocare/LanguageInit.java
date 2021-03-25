package com.cat.novocare;

import android.content.Context;
import android.content.SharedPreferences;

public class LanguageInit {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("APP_PREF", Context.MODE_PRIVATE);
    }

    public static void initLanguage(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(SELECTED_LANGUAGE, true);
        editor.apply();
    }

    public static boolean getLanguageInit(Context context) {
        return getSharedPreferences(context).getBoolean(SELECTED_LANGUAGE, false);
    }
}
