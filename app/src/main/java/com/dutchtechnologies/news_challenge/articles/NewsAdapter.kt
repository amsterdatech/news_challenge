package com.dutchtechnologies.news_challenge.articles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dutchtechnologies.news_challenge.model.Article
import com.dutchtechnologies.news_challenge.R
import kotlinx.android.synthetic.main.view_holder_headline_article.view.*
import kotlinx.android.synthetic.main.view_holder_regular_article.view.*
import load
import kotlin.properties.Delegates

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<Article> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }
    lateinit var click: View.OnClickListener

    companion object {
        const val HEADLINE = 0
        const val REGULAR = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADLINE -> {
                HeadlineViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.view_holder_headline_article,
                        parent,
                        false
                    ), click
                )
            }

            else -> {
                RegularViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.view_holder_regular_article,
                        parent,
                        false
                    ), click
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return HEADLINE
        }
        return REGULAR
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleViewHolder).bindViews(items[position])
    }

    interface ArticleViewHolder {
        fun bindViews(article: Article)
    }


    class RegularViewHolder(var containerView: View, var onClick: View.OnClickListener? = null) :
        RecyclerView.ViewHolder(containerView),
        ArticleViewHolder {
        override fun bindViews(article: Article) {
            containerView.tag = this
            containerView.setOnClickListener(onClick)
            with(article) {
                containerView.view_holder_regular_thumbnail.load(thumbnail)
                containerView.view_holder_regular_article_title.text = title
                containerView.view_holder_regular_article_date_and_author.text =
                    "${this.publishedDay} by ${this.author}"
            }
        }


    }

    class HeadlineViewHolder(var containerView: View, var onClick: View.OnClickListener? = null) :
        RecyclerView.ViewHolder(containerView),
        ArticleViewHolder {

        override fun bindViews(article: Article) {
            containerView.tag = this
            containerView.setOnClickListener(onClick)
            with(article) {
                containerView.view_holder_headline_thumbnail.load(thumbnail, false)
                containerView.view_holder_headline_article_title.text = title
                containerView.view_holder_headline_article_date_and_author.text =
                    "${this.publishedDay} by ${this.author}"
            }
        }

    }
}
