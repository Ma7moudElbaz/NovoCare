package com.cat.novocare.main_activity.more;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cat.novocare.R;
import com.cat.novocare.language_utils.LanguageUtils;
import com.cat.novocare.language_utils.LocaleHelper;
import com.cat.novocare.main_activity.MainActivity;

public class MoreFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    public void recreateTask(final Context context) {
        final PackageManager pm = context.getPackageManager();
        final Intent intent = pm.getLaunchIntentForPackage(context.getPackageName());
        final ComponentName componentName = intent.getComponent();
        final Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }

    MainActivity activity;

    TextView arBtn, enBtn;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arBtn = view.findViewById(R.id.ar_btn);
        enBtn = view.findViewById(R.id.en_btn);

        activity = (MainActivity) getActivity();


        setLangButtons(LanguageUtils.getLanguage(getActivity()));

        arBtn.setOnClickListener(v -> {
            if (!(LanguageUtils.getLanguage(activity).equals("ar"))) {
                LocaleHelper.changeLanguage(activity, "ar");
                recreateTask(activity);
            }
        });

        enBtn.setOnClickListener(v -> {
            if (LanguageUtils.getLanguage(activity).equals("ar")) {
                LocaleHelper.changeLanguage(activity, "en");
                recreateTask(activity);
            }
        });
    }

    private void setLangButtons(String lang) {

        if (lang.equals("ar")) {
            arBtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.blue_button_bg));
            arBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

            enBtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.gray_border_button_bg));
            enBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.dark_blue));
        } else {
            enBtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.blue_button_bg));
            enBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

            arBtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.gray_border_button_bg));
            arBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.dark_blue));
        }
    }
}