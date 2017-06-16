package com.kshrd.articlecms;

import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kshrd.articlecms.entity.ArticleResponse;
import com.kshrd.articlecms.entity.UpdateArticleResponse;
import com.kshrd.articlecms.event.ArticleUpdateEvent;
import com.kshrd.articlecms.form.UpdateArticleForm;
import com.kshrd.articlecms.webservice.ArticleService;
import com.kshrd.articlecms.webservice.ServiceGenerator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TextWatcher, MyClickListener {

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

    @Override
    public void onClicked(int position, View view) {
        final ArticleResponse.Article article = articleAdapter.getArticle(position);
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
        popupMenu.inflate(R.menu.my_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        Toast.makeText(MainActivity.this, String.valueOf(article.getId()), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.update:
                        DialogFragment fragment = MyDialogFragment.newInstance(article);
                        fragment.show(getSupportFragmentManager(), "MyDialogFragment");


                        break;
                }
                return true;
            }

        });
        popupMenu.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onArticleUpdateEvent(ArticleUpdateEvent event){
        final ArticleResponse.Article article = event.getArticle();
        UpdateArticleForm form = new UpdateArticleForm(
            article.getTitle(),
                article.getDescription(),
                article.getAuthor().getId(),
                article.getCategory().getId(),
                article.getStatus(),
                article.getImage()
        );

        Call<UpdateArticleResponse> updateArticle = articleService.updateArticle(article.getId(), form);
        updateArticle.enqueue(new Callback<UpdateArticleResponse>() {
            @Override
            public void onResponse(Call<UpdateArticleResponse> call, Response<UpdateArticleResponse> response) {
                articleAdapter.updateItemOf(response.body().getArticle());
            }

            @Override
            public void onFailure(Call<UpdateArticleResponse> call, Throwable t) {

            }
        });
    }
}
