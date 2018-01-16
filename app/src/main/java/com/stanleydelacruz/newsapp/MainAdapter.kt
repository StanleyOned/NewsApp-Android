package com.stanleydelacruz.newsapp

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_row.view.*;

/**
 * Created by stanleydelacruz on 1/15/18.
 */

class MainAdapter(val news: ArrayList<News>): RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {

        return news.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.news_row, parent,false)
        return  CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {

        val article =  news.get(position)

        holder?.view?.author_name?.text = article.author
        holder?.view?.description?.text = article.description

        Picasso.with(holder?.view?.context).load(article.urlToImage).into(holder?.view?.imageView)

        holder?.article = article
    }
}

class CustomViewHolder(val view: View, var article: News? = null): RecyclerView.ViewHolder(view) {

    companion object {
        val WEBVIEW_KEY = "WEBVIEW_KEY"
    }

    init {
        view.setOnClickListener {

            val intent = Intent(view.context, WebActivity::class.java)
            intent.putExtra(WEBVIEW_KEY,article?.url)
            view.context.startActivity(intent)
        }
    }
}