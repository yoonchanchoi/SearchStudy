package com.example.searchstudy.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import com.example.searchstudy.databinding.ActivityMainBinding
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    private val recentFragment = RecentFragment()

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }
    private fun init(){
        initListener()
    }

    private fun initListener(){
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(str: String?): Boolean {
                str?.let {
                    viewModel.addSearchData(str)
                }
                return true
            }

            override fun onQueryTextChange(str: String?): Boolean {
                return true
            }

        })
    }
}