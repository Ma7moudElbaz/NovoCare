package com.cat.novocare.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceInterface {

    @GET("posts")
    Call<ResponseBody> getNews(@Query("lang") String lang,@Query("page") int pageNo);


}