package com.cat.novocare.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceInterface {

    @GET("posts")
    Call<ResponseBody> getNews(@Query("lang") String lang,@Query("page") int pageNo);

    @GET("faqs")
    Call<ResponseBody> getFaqs(@Query("lang") String lang,@Query("page") int pageNo);

    @GET("abouts")
    Call<ResponseBody> getAbouts(@Query("lang") String lang);

    @GET("terms")
    Call<ResponseBody> getTerms(@Query("lang") String lang);

    @GET("privacy")
    Call<ResponseBody> getPrivacy(@Query("lang") String lang);

}