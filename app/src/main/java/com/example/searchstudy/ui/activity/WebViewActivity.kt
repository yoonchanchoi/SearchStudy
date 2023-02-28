package com.example.searchstudy.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import com.example.searchstudy.databinding.ActivityMainBinding
import com.example.searchstudy.databinding.ActivityWebViewBinding
import com.example.searchstudy.ui.dialog.LoadingProgressDialog
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    private lateinit var ditailLoadUrl: String // 로딩될 Url

    private lateinit var webView: WebView

    private lateinit var progressBar: LoadingProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initData()
        webViewSettiong()
    }

    private fun initData() {
        ditailLoadUrl = intent.getStringExtra(Constants.DITAIL_WEB_LOAD_URL).toString()
    }

    private fun webViewSettiong() {
        progressBar = LoadingProgressDialog(this)
        webView = binding.wv
//        webView.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        webView.webViewClient = object: WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.dismiss()
            }

        }
        val wvSetting = webView.settings
        wvSetting.javaScriptEnabled = true // 웹페이지 자바스클비트 허용 여부
        wvSetting.builtInZoomControls = false // 화면 확대 축소 허용 여부
        wvSetting.useWideViewPort = true // 화면 사이즈 맞추기 허용 여부

        binding.wv.loadUrl(ditailLoadUrl)
//        "http://web-inf.tistory.com"

    }
}