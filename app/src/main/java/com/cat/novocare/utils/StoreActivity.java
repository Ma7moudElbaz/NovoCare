package com.cat.novocare.utils;


import androidx.annotation.NonNull;

import com.cat.novocare.network.Webservice;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreActivity {

    public static void addScreenActivity(String screen, String device_id) {
        Map<String, String> map = new HashMap<>();
        map.put("mac_address", device_id);
        map.put("screen", screen);

        Webservice.getInstance().getApi().addDeviceActivity(map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
            }
        });
    }

}
