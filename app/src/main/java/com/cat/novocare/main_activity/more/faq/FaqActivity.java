package com.cat.novocare.main_activity.more.faq;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
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



public class FaqActivity extends LocalizationActivity {

    ArrayList<Faq_item> faq_list;
    RecyclerView faqRecycler;
    Faq_adapter faq_adapter;

    ProgressBar loading;
    int currentPageNum = 1;
    int lastPageNum;
    boolean mHasReachedBottomOnce = false;
    String lang;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        lang = Locale.getDefault().toString();

        loading = findViewById(R.id.loading);
        faqRecycler = findViewById(R.id.recycler);
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> onBackPressed());

        faq_list = new ArrayList<>();

        initFaqRecyclerView();
        loadFaqData(lang, currentPageNum);
    }

    public void loadFaqData(String lang, int pageNum) {
        loading.setVisibility(View.VISIBLE);

        Webservice.getInstance().getApi().getFaqs(lang, pageNum).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                try {
                    assert response.body() != null;
                    JSONObject responseObject = new JSONObject(response.body().string());
                    JSONArray faqArray = responseObject.getJSONArray("data");
                    setFaq(faqArray);
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
                Toast.makeText(getBaseContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
            }
        });
    }

    public void setFaq(JSONArray list) {
        try {
            for (int i = 0; i < list.length(); i++) {
                JSONObject currentObject = list.getJSONObject(i);
                final String question = currentObject.getString("question");
                final String answer = currentObject.getString("answer");

                faq_list.add(new Faq_item(question, answer, false));

            }
            faq_adapter.notifyDataSetChanged();
            mHasReachedBottomOnce = false;
            currentPageNum++;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void initFaqRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        faqRecycler.setLayoutManager(layoutManager);
        faq_adapter = new Faq_adapter(faq_list);
        faqRecycler.setAdapter(faq_adapter);

        faqRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && !mHasReachedBottomOnce) {
                    mHasReachedBottomOnce = true;

                    if (currentPageNum <= lastPageNum)
                        loadFaqData(lang, currentPageNum);

                }
            }
        });


    }
}