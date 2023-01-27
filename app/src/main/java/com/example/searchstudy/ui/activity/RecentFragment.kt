//package com.example.searchstudy.ui.activity
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.activity.viewModels
//import androidx.fragment.app.activityViewModels
//import androidx.fragment.app.viewModels
//import com.example.searchstudy.R
//import com.example.searchstudy.databinding.FragmentRecentBinding
//import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
//import com.example.searchstudy.ui.viewmodels.RecentFragmentViewModel
//import dagger.hilt.android.AndroidEntryPoint
//import javax.inject.Inject
//
//@AndroidEntryPoint
//class RecentFragment() : Fragment() {
//
//    private val mainViewModel: MainActivityViewModel by activityViewModels()
//
//    private lateinit var binding: FragmentRecentBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentRecentBinding.inflate(inflater, container, false)
//        init()
//        return binding.root
//    }
//
//    private fun init(){
//        initData()
//    }
//    private fun initData(){
//
//    }
//}