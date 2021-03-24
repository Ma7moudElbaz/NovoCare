package com.cat.novocare.main_activity.home;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.cat.novocare.R;

public class ContactUsActivity extends LocalizationActivity {

    ImageView call, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        call = findViewById(R.id.call);
        back = findViewById(R.id.back);

        back.setOnClickListener(v -> onBackPressed());

        call.setOnClickListener(v -> {
            String phone = "19456";

            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        });
    }
}