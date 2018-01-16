package com.stanleydelacruz.newsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_news.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        recycler_main.layoutManager = LinearLayoutManager(this)

        fetchJson()
    }

    private fun fetchJson() {

        val yourApiKey = "112312e2a7b84e22a5ec43655fb52aa9"
        //get your api key from newsapi.org
        val url = "https://newsapi.org/v1/articles?source=IGN&sortBy=top&apiKey="+ yourApiKey

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue( object : Callback {

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val articles = JSONObject(body)
                val articlesArray = articles.getJSONArray("articles")
                val gson = GsonBuilder().create()

                val i: Int = 0
                val size: Int = articlesArray.length()

                var arrayOfNews = arrayListOf<News>()

                for (i in 0.. size-1) {

                    var json_objectDetail = articlesArray.getJSONObject(i)

                    val urlToImage = json_objectDetail.getString("urlToImage")
                    var author = json_objectDetail.getString("author")

                    if(author == "null") {
                        author = "Unknown Author"
                    }
                    val  url = json_objectDetail.getString("url")
                    val description = json_objectDetail.getString("description")

                    val news = News(urlToImage,author,url,description)

                    arrayOfNews.add(news)
                }

                println(arrayOfNews.count())

                runOnUiThread {
                    recycler_main.adapter = MainAdapter(arrayOfNews)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {

            }

        })

    }
}

class News(var urlToImage: String, var author: String? = "Unknown Author", var url: String, var description: String)
