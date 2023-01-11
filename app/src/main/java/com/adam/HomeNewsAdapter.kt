package com.adam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class HomeNewsAdapter(
    var arrNews:MutableList<NewsArticle>,
    var onClick:(position:Int)->Unit
):RecyclerView.Adapter<HomeNewsAdapter.NewsHolder>() {
    class NewsHolder(view: View):RecyclerView.ViewHolder(view) {
        val news_item_ivThumbnail =view.findViewById<ImageView>(R.id.news_item_ivThumbnail)
        val news_item_tvTitle =view.findViewById<TextView>(R.id.news_item_tvTitle)
        val news_item_tvAuthor =view.findViewById<TextView>(R.id.news_item_tvAuthor)
        val news_item_tvPublishedAt =view.findViewById<TextView>(R.id.news_item_tvPublishedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        var i = LayoutInflater.from(parent.context)
        return NewsHolder(i.inflate(R.layout.news_recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val item = arrNews[position]
        Picasso.get().load(item.urlToImage).into(holder.news_item_ivThumbnail)
        holder.news_item_tvTitle.text = item.title
        holder.news_item_tvAuthor.text = item.author
        holder.news_item_tvPublishedAt.text = item.publishedAt
        holder.itemView.setOnClickListener {
            onClick.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return arrNews.size
    }

}