package com.cat.novocare.main_activity.home;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.cat.novocare.R;
import com.cat.novocare.network.Webservice;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactEmailActivity extends LocalizationActivity {

    EditText name,email, message;
    TextView sendBtn;
    ProgressBar loading;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_email);

        back = findViewById(R.id.back);
        loading = findViewById(R.id.loading);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        message = findViewById(R.id.message);
        sendBtn = findViewById(R.id.send);

        back.setOnClickListener(v -> onBackPressed());

        sendBtn.setOnClickListener(v -> sendEmail());
    }


    public void sendEmail() {
        Map<String, String> map = new HashMap<>();
        final String nameTxt = name.getText().toString();
        final String emailTxt = email.getText().toString();
        final String messageTxt = message.getText().toString();

        if (nameTxt.length() == 0 || emailTxt.length() == 0|| messageTxt.length() == 0) {
            Toast.makeText(getBaseContext(), R.string.fill_fields, Toast.LENGTH_SHORT).show();
        } else {
            loading.setVisibility(View.VISIBLE);
            map.put("name", nameTxt);
            map.put("email", emailTxt);
            map.put("comment", messageTxt);
            Webservice.getInstance().getApi().sendEmail(map).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                    if (response.code() == 200) {
                        Toast.makeText(getBaseContext(), R.string.email_sent, Toast.LENGTH_LONG).show();
                        onBackPressed();
                    } else {
                        try {
                            assert response.errorBody() != null;
                            Toast.makeText(getBaseContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    loading.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Toast.makeText(getBaseContext(), R.string.network_error, Toast.LENGTH_SHORT).show();

                    loading.setVisibility(View.GONE);
                }
            });
        }
    }
}