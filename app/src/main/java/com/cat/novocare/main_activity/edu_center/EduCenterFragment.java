package com.cat.novocare.main_activity.edu_center;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cat.novocare.R;
import com.cat.novocare.network.Webservice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class EduCenterFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edu_center, container, false);
    }


    ArrayList<News_item> news_list;
    RecyclerView newsRecycler;
    News_adapter news_adapter;

    ProgressBar loading;
    int currentPageNum = 1;
    int lastPageNum;
    boolean mHasReachedBottomOnce = false;
    String lang;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lang = Locale.getDefault().toString();

        loading = view.findViewById(R.id.loading);
        newsRecycler = view.findViewById(R.id.recycler);

        news_list = new ArrayList<>();

        initNewsRecyclerView();
        loadNewsData(lang, currentPageNum);
    }

    public void loadNewsData(String lang, int pageNum) {
        loading.setVisibility(View.VISIBLE);

        Webservice.getInstance().getApi().getNews(lang, pageNum).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                try {
                    JSONObject responseObject = new JSONObject(response.body().string());
                    JSONArray newsArray = responseObject.getJSONArray("data");
                    setNews(newsArray);
                    JSONObject metaObject = responseObject.getJSONObject("meta");
                    lastPageNum = metaObject.getInt("last_page");

                    loading.setVisibility(View.GONE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d("Error Throw", t.toString());
                Log.d("commit Test Throw", t.toString());
                Log.d("Call", t.toString());
                Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
            }
        });
    }

    public void setNews(JSONArray list) {
        try {
            for (int i = 0; i < list.length(); i++) {
                JSONObject currentobject = list.getJSONObject(i);
                final String title = currentobject.getString("title");
                final String caption = currentobject.getString("caption");
                final String imageUrl = currentobject.getString("image");
                final String content = currentobject.getString("text");
                final String date = currentobject.getString("created_at");

                news_list.add(new News_item(title, caption, imageUrl, content, date));

            }

            news_adapter.notifyDataSetChanged();
            mHasReachedBottomOnce = false;
            currentPageNum++;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void initNewsRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        newsRecycler.setLayoutManager(layoutManager);
        news_adapter = new News_adapter(getContext(), news_list);
        newsRecycler.setAdapter(news_adapter);

        newsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && !mHasReachedBottomOnce) {
                    mHasReachedBottomOnce = true;

                    if (currentPageNum <= lastPageNum)
                        loadNewsData(lang, currentPageNum);

                }
            }
        });


    }
}