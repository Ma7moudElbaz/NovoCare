package com.cat.novocare.main_activity.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cat.novocare.R;
import com.cat.novocare.network.Webservice;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactEmailActivity extends AppCompatActivity {

    EditText email, message;
    TextView sendBtn;
    String name;
    ProgressBar loading;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_email);
        name = getIntent().getStringExtra("name");

        back = findViewById(R.id.back);
        loading = findViewById(R.id.loading);
        email = findViewById(R.id.email);
        message = findViewById(R.id.message);
        sendBtn = findViewById(R.id.send);

        back.setOnClickListener(v -> onBackPressed());

        sendBtn.setOnClickListener(v -> sendEmail());
    }


    public void sendEmail() {
        Map<String, String> map = new HashMap<>();
        final String emailTxt = email.getText().toString();
        final String messageTxt = message.getText().toString();

        if (emailTxt.length() == 0 || messageTxt.length() == 0) {
            Toast.makeText(getBaseContext(), R.string.fill_fields, Toast.LENGTH_SHORT).show();
        } else {
            loading.setVisibility(View.VISIBLE);
            map.put("name", name);
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