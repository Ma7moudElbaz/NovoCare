package com.cat.novocare.main_activity.home;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.cat.novocare.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;


public class ContactUsActivity extends LocalizationActivity {

    ImageView call, back;
    TextView startCallBtn;
    LinearLayout chat, email;
    ImageView chatImg, emailImg;
    TextView chatTxt, emailTxt;
    EditText name;

    int selectedItem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        call = findViewById(R.id.call);
        back = findViewById(R.id.back);
        name = findViewById(R.id.name);
        chat = findViewById(R.id.chat);
        email = findViewById(R.id.email);
        chatImg = findViewById(R.id.chat_img);
        emailImg = findViewById(R.id.email_img);
        chatTxt = findViewById(R.id.chat_txt);
        emailTxt = findViewById(R.id.email_txt);
        startCallBtn = findViewById(R.id.start_call);

        back.setOnClickListener(v -> onBackPressed());

        chat.setOnClickListener(v -> setChatActive());
        email.setOnClickListener(v -> setEmailActive());

        startCallBtn.setOnClickListener(v -> startCall());

        call.setOnClickListener(v -> {
            String phone = "19456";

            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        });
    }


    private void setChatActive() {
        selectedItem = 2;
        resetActive();
        chat.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.light_blue_bg));
        chatImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.white));
        chatTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
        startCallBtn.setText(R.string.start_call);
    }

    private void setEmailActive() {
        selectedItem = 3;
        resetActive();
        email.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.light_blue_bg));
        emailImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.white));
        emailTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
        startCallBtn.setText(R.string.send_email_form);
    }

    private void resetActive() {
        chat.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.blue_border_button_bg));
        chatImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));
        chatTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));

        email.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.blue_border_button_bg));
        emailImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));
        emailTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));
    }

    private void startCall() {
        if (selectedItem == 0) {
            Toast.makeText(this, R.string.select_contact_way, Toast.LENGTH_SHORT).show();
        } else if (selectedItem == 2) {
            Dexter.withContext(getBaseContext())
                    .withPermissions(
                            Manifest.permission.CAMERA,
                            Manifest.permission.RECORD_AUDIO
                    ).withListener(new MultiplePermissionsListener() {
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                    if (multiplePermissionsReport.areAllPermissionsGranted()) {
                        Intent i = new Intent(getBaseContext(), ContactUsChatActivity.class);
//                        Intent i = new Intent(getBaseContext(), TestWebViewActivity.class);
                        startActivity(i);
                    }
                }
                @Override
                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                }
            }).check();

        } else if (selectedItem == 3) {
            Intent i = new Intent(getBaseContext(), ContactEmailActivity.class);
            startActivity(i);
        }

    }


}