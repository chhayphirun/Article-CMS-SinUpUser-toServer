package com.kshrd.articlecms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.kshrd.articlecms.entity.ArticleResponse;
import com.kshrd.articlecms.event.ArticleUpdateEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pirang on 6/16/17.
 */

public class MyDialogFragment extends DialogFragment {

    private static final String ARTICLE = "article";
    private ArticleResponse.Article article;

    @BindView(R.id.etTitle)
    EditText etTitle;

    public static MyDialogFragment newInstance(ArticleResponse.Article article){
        MyDialogFragment fragment = new MyDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARTICLE, article);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_update_article, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null){
            article = getArguments().getParcelable(ARTICLE);
            etTitle.setText(article.getTitle());
        }
    }

    @OnClick(R.id.btnUpdate)
    protected void onUpdateClicked(){

        article.setTitle(etTitle.getText().toString());
        EventBus.getDefault().post(new ArticleUpdateEvent(article));
        dismiss();
    }
}
