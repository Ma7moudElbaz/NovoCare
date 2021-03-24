package com.cat.novocare.main_activity.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cat.novocare.R;
import com.cat.novocare.network.Webservice;

import org.json.JSONObject;

import java.util.Locale;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    TextView homeText,contactUs;
    ImageView homeImage;
    ProgressBar loading;
    String lang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeText = view.findViewById(R.id.text_home);
        homeImage = view.findViewById(R.id.image_home);
        loading = view.findViewById(R.id.loading);
        contactUs = view.findViewById(R.id.contactUs);

        contactUs.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(),ContactUsActivity.class);
            startActivity(i);
        });


        lang = Locale.getDefault().toString();
        getData(lang);
    }

    public void getData(String lang) {
        loading.setVisibility(View.VISIBLE);

        Webservice.getInstance().getApi().getHome(lang).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                try {
                    assert response.body() != null;
                    JSONObject responseObject = new JSONObject(response.body().string());
                    JSONObject dataObject = responseObject.getJSONObject("data");
                    String imageUrl = dataObject.getString("image");
                    String text = dataObject.getString("caption");

                    homeText.setText(Html.fromHtml(text));
                    Glide.with(Objects.requireNonNull(getContext())).load(imageUrl).placeholder(R.drawable.image_loading).into(homeImage);

                    loading.setVisibility(View.GONE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d("Error Throw", t.toString());
                Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
            }
        });
    }

}