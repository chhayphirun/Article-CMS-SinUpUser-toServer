package com.kshrd.articlecms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kshrd.articlecms.entity.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pirang on 6/14/17.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    List<ArticleResponse.Article> articleList;
    MyClickListener clickListener;

    public ArticleAdapter() {
        articleList = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        clickListener = (MyClickListener) recyclerView.getContext();
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {

        ArticleResponse.Article article = articleList.get(position);

        holder.tvTitle.setText(article.getTitle());
        holder.tvDescription.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    public void addMoreItems(List<ArticleResponse.Article> articleList){
        this.articleList.addAll(articleList);
        notifyDataSetChanged();
    }

    public void clearList(){
        this.articleList.clear();
    }

    public ArticleResponse.Article getArticle(int pos){
        return this.articleList.get(pos);
    }


    /**
     * View Holder
     */
    class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvDescription)
        TextView tvDescription;

        @BindView(R.id.ivMore)
        ImageView ivMore;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ivMore.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClicked(getAdapterPosition(), view);
        }
    }

}
