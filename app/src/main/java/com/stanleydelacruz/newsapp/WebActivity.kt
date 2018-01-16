package com.stanleydelacruz.newsapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.webview_layout.*

/**
 * Created by stanleydelacruz on 1/15/18.
 */

class WebActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.webview_layout)

        val url = intent.getStringExtra(CustomViewHolder.WEBVIEW_KEY)
        webview.webViewClient = WebViewClient()
        webview.loadUrl(url)

        webview.settings.javaScriptEnabled = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
    }
}