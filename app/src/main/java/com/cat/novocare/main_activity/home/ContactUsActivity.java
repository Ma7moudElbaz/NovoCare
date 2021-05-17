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

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.cat.novocare.R;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


public class ContactUsActivity extends LocalizationActivity implements EasyPermissions.PermissionCallbacks {

    ImageView call, back;
    TextView startCallBtn;
    LinearLayout videoCall, chat, email;
    ImageView videoCallImg, chatImg, emailImg;
    TextView videoCallTxt, chatTxt, emailTxt;
    EditText name;

    int selectedItem = 0;

    private static final int REQUEST_CAMERA_PERMISSION = 1001;
    private static final int REQUEST_AUDIO_PERMISSION = 1002;

    String[] perms = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};

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
        videoCall.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.blue_border_button_bg));
        videoCallImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));
        videoCallTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));

        chat.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.blue_border_button_bg));
        chatImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));
        chatTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));

        email.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.blue_border_button_bg));
        emailImg.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));
        emailTxt.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));
    }

    private void startCall() {
        String nameTxt = name.getText().toString();
        if (nameTxt.length() == 0) {
            Toast.makeText(this, R.string.add_name, Toast.LENGTH_SHORT).show();
        } else if (selectedItem == 0) {
            Toast.makeText(this, R.string.select_contact_way, Toast.LENGTH_SHORT).show();
        }else if (selectedItem == 1){
//            Intent i = new Intent(getBaseContext(), ContactUsChatActivity.class);
//            i.putExtra("name", nameTxt);
//            startActivity(i);
        }else if (selectedItem == 2){
            if (EasyPermissions.hasPermissions(this,perms)){
                Intent i = new Intent(getBaseContext(), ContactUsChatActivity.class);
                i.putExtra("name", nameTxt);
                startActivity(i);
            }else {
                grantPermissions();
            }

        }
        else if (selectedItem == 3) {
            Intent i = new Intent(getBaseContext(), ContactEmailActivity.class);
            i.putExtra("name", nameTxt);
            startActivity(i);
        }

    }

    private void grantPermissions(){
        EasyPermissions.requestPermissions(this, "Permission request",
                REQUEST_CAMERA_PERMISSION, perms);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        startCall();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "You must grant all permissions to use ths feature", Toast.LENGTH_SHORT).show();
    }
}