package com.cat.novocare.main_activity.home;


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

import io.customerly.Customerly;


public class ContactUsActivity extends LocalizationActivity {

    ImageView call, back;
    TextView startCallBtn;
    LinearLayout videoCall, chat, email;
    ImageView videoCallImg, chatImg, emailImg;
    TextView videoCallTxt, chatTxt, emailTxt;
    EditText name;

    int selectedItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        call = findViewById(R.id.call);
        back = findViewById(R.id.back);
        name = findViewById(R.id.name);
        videoCall = findViewById(R.id.video_call);
        chat = findViewById(R.id.chat);
        email = findViewById(R.id.email);
        videoCallImg = findViewById(R.id.video_call_img);
        chatImg = findViewById(R.id.chat_img);
        emailImg = findViewById(R.id.email_img);
        videoCallTxt = findViewById(R.id.video_call_txt);
        chatTxt = findViewById(R.id.chat_txt);
        emailTxt = findViewById(R.id.email_txt);
        startCallBtn = findViewById(R.id.start_call);

        back.setOnClickListener(v -> onBackPressed());

        videoCall.setOnClickListener(v -> setVideoCallActive());
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

    private void setVideoCallActive() {
        selectedItem = 1;
        resetActive();
        videoCall.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.light_blue_bg));
        videoCallImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.white));
        videoCallTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
    }

    private void setChatActive() {
        selectedItem = 2;
        resetActive();
        chat.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.light_blue_bg));
        chatImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.white));
        chatTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
    }

    private void setEmailActive() {
        selectedItem = 3;
        resetActive();
        email.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.light_blue_bg));
        emailImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.white));
        emailTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
    }

    private void resetActive() {
        videoCall.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.gray_border_curved_bg));
        videoCallImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.gray));
        videoCallTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray));

        chat.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.gray_border_curved_bg));
        chatImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.gray));
        chatTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray));

        email.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.gray_border_curved_bg));
        emailImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.gray));
        emailTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
    }

    private void startCall() {
        String nameTxt = name.getText().toString();
        if (nameTxt.length() == 0) {
            Toast.makeText(this, R.string.add_name, Toast.LENGTH_SHORT).show();
        } else if (selectedItem == 0) {
            Toast.makeText(this, R.string.select_contact_way, Toast.LENGTH_SHORT).show();
        }else if (selectedItem == 1){
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cat-sw.com/clickdesk/customerly.php"));
            startActivity(i);
        }else if (selectedItem == 2){
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cat-sw.com/clickdesk/customerly.php"));
            startActivity(i);
        }
        else if (selectedItem == 3) {
            Intent i = new Intent(getBaseContext(), ContactEmailActivity.class);
            i.putExtra("name", nameTxt);
            startActivity(i);
        }

    }
}