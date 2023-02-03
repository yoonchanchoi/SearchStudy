package com.example.searchstudy.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.searchstudy.databinding.ActivityFirstSearchBinding
import com.example.searchstudy.databinding.ActivityMainBinding
import com.example.searchstudy.util.Pref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint

class FirstSearchActivity : AppCompatActivity() {


    @Inject
    lateinit var pref: Pref
    private lateinit var binding: ActivityFirstSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}