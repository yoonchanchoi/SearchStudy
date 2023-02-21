package com.example.searchstudy.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.searchstudy.databinding.ActivityTestBinding
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private var blogCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTest.setOnClickListener {
            viewModel.requestBlog("롤 hdv", blogCount + 1)
        }
        viewModel.blogResultSearchArraylist.observe(this){
            Log.e("cyc","")
            Log.e("cyc","-------------------시작--------------------")
            for(i in it.allItems.indices){
                Log.e("cyc","it.allItems[$i]--->${it.allItems[i]}")
            }
            Log.e("cyc","-------------------끝--------------------")
            Log.e("cyc","")
            blogCount += it.allItems.size
            Log.e("cyc","===========================================")
            Log.e("cyc","it.allItems.size->${it.allItems.size}")
            Log.e("cyc","blogCount->${blogCount}")
            Log.e("cyc","===========================================")
        }
    }
}