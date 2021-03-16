package com.cat.novocare.main_activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cat.novocare.R;
import com.cat.novocare.language_utils.LanguageUtils;
import com.cat.novocare.language_utils.LocaleHelper;

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


    TextView arBtn,enBtn;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arBtn = view.findViewById(R.id.ar_btn);
        enBtn = view.findViewById(R.id.en_btn);

        setLangButtons(LanguageUtils.getLanguage(getActivity()));

        arBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            LocaleHelper.changeLanguage(getActivity(), "ar");
            recreateTask(getActivity());
            }
        });

        enBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            LocaleHelper.changeLanguage(getActivity(), "en");
            recreateTask(getActivity());
            }
        });
    }

    private void setLangButtons(String lang){
//        if (lang.equals("ar")){
//            arBtn.setBackground(R.drawable.blue_button_bg);
//        }else {
//
//        }
    }
}