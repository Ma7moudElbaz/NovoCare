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
import com.cat.novocare.main_activity.MainActivity;
import com.cat.novocare.main_activity.more.faq.FaqActivity;

import java.util.Locale;

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
    TextView faq, about, terms, privacy;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arBtn = view.findViewById(R.id.ar_btn);
        enBtn = view.findViewById(R.id.en_btn);
        faq = view.findViewById(R.id.faq);
        about = view.findViewById(R.id.about);
        terms = view.findViewById(R.id.terms);
        privacy = view.findViewById(R.id.privacy);


        faq.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), FaqActivity.class);
            startActivity(i);
        });

        about.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), AboutUsActivity.class);
            startActivity(i);
        });

        terms.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), TermsActivity.class);
            startActivity(i);
        });

        privacy.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), PrivacyActivity.class);
            startActivity(i);
        });
        activity = (MainActivity) getActivity();

        activity.setMore();
        setLangButtons(Locale.getDefault().toString());

        arBtn.setOnClickListener(v -> {
            activity.setLanguage("ar");
        });

        enBtn.setOnClickListener(v -> {
            activity.setLanguage("en");
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