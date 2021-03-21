package com.cat.novocare.main_activity.more;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cat.novocare.R;
import com.cat.novocare.network.Webservice;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cat.novocare.language_utils.LanguageUtils.getLanguage;

public class AboutUsActivity extends AppCompatActivity {

    TextView details;
    ImageView back;
    ProgressBar loading;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        details = findViewById(R.id.details);
        back = findViewById(R.id.back);
        loading = findViewById(R.id.loading);

        back.setOnClickListener(v -> onBackPressed());

        lang = getLanguage(getBaseContext());
        getData(lang);
    }

    public void getData(String lang) {
        loading.setVisibility(View.VISIBLE);

        Webservice.getInstance().getApi().getAbouts(lang).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                try {
                    JSONObject responseObject = new JSONObject(response.body().string());
                    JSONObject dataObject = responseObject.getJSONObject("data");
                    String detailsStr = dataObject.getString("caption");

                    details.setText(Html.fromHtml(detailsStr));
                    loading.setVisibility(View.GONE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d("Error Throw", t.toString());
                Toast.makeText(getBaseContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
            }
        });
    }
}