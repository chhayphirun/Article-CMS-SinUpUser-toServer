package com.kshrd.articlecms;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.kshrd.articlecms.entity.ArticleResponse;
import com.kshrd.articlecms.webservice.ArticleService;
import com.kshrd.articlecms.webservice.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TextWatcher{

    @BindView(R.id.rvArticle)
    RecyclerView rvArticle;

    @BindView(R.id.etKeyword)
    EditText etKeyword;

    ArticleAdapter articleAdapter;
    ArticleService articleService;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Initialization
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                loadArticles(etKeyword.getText().toString());
            }
        };

        setRecyclerView();

        articleService = ServiceGenerator.createService(ArticleService.class);
        loadArticles(etKeyword.getText().toString());
        etKeyword.addTextChangedListener(this);

        // Code..

    }

    private void loadArticles(String keyword) {
        Call<ArticleResponse> call = articleService.findArticleByTitle(keyword);
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                ArticleResponse articleResponse = response.body();
                articleAdapter.clearList();
                articleAdapter.addMoreItems(articleResponse.getArticlelist());
                Log.e("ooooo", articleAdapter.getItemCount()+ "");

            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setRecyclerView() {
        articleAdapter = new ArticleAdapter();
        rvArticle.setLayoutManager(new LinearLayoutManager(this));
        rvArticle.setAdapter(articleAdapter);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence keyword, int i, int i1, int i2) {
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 1000);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
