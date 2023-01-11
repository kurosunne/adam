package com.adam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class HomeFragment : Fragment() {

    private lateinit var home_tvTitle: TextView
    private lateinit var home_rvNews: RecyclerView
    private var arrNews:MutableList<NewsArticle> = mutableListOf()
    private lateinit var homeNewsAdapter: HomeNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home_tvTitle = view.findViewById(R.id.home_tvTitle)
        home_rvNews = view.findViewById(R.id.home_rvNews)

        val strReq = object: StringRequest(
            Method.GET,
            "https://newsapi.org/v2/top-headlines?country=id&apiKey=bbde352252c847a6baf3f28c8d1520d8&pageSize=10&page=1",
            Response.Listener {
                val res = JSONObject(it)
                arrNews.clear()
                val articles = res.getJSONArray("articles")
                for (i in 0 until articles.length()){
                    val article = articles.getJSONObject(i)
                    val article__source = article.getJSONObject("source").getString("name")
                    val article__title = article.getString("title")
                    val article__author = article.getString("author")
                    val article__description = article.getString("description")
                    val article__url = article.getString("url")
                    val article__urlToImage = article.getString("urlToImage")
                    val article__publishedAt = article.getString("publishedAt")
                    arrNews.add(NewsArticle(article__source, article__author,article__title,article__description,article__url,article__urlToImage,article__publishedAt))
                }
                homeNewsAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener {
                Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"]="Mozilla/5.0"
                return headers
            }
        }
        val queue: RequestQueue = Volley.newRequestQueue(context)
        queue.add(strReq)
        
        homeNewsAdapter = HomeNewsAdapter(arrNews){

        }
        home_rvNews.adapter = homeNewsAdapter
        home_rvNews.layoutManager = LinearLayoutManager(context)
        homeNewsAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}