package com.example.searchstudy.ui.dialog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.searchstudy.R
import com.example.searchstudy.databinding.FragmentAdultWarningDialogBinding
import com.example.searchstudy.databinding.FragmentImgDialogBinding
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImgDialogFragment : DialogFragment() {

//    private val viewModel: MainActivityViewModel by activityViewModels()


    private lateinit var binding: FragmentImgDialogBinding
    private lateinit var imgDitailUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //다이털로그가 화면에 꽉 차게 설정

        setStyle(STYLE_NO_FRAME,R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentImgDialogBinding.inflate(inflater, container, false)
        arguments?.let {
            imgDitailUrl = it.getString(Constants.DITAIL_IMG_LOAD_URL).toString()
//            Log.e("cyc","imgDitailUrl--->${imgDitailUrl}")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        //다이털로그가 화면에 꽉 차게 설정
//        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) 화면의 꽉찬 정도
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))                             배경색 지정
//        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)                              dim 배경의 불투명도 제거

        Glide.with(requireContext())
            .load(imgDitailUrl)
            .into(binding.iv)
    }
}























//@AndroidEntryPoint
//class ImgDialogFragment : DialogFragment() {
//
//    private val viewModel: MainActivityViewModel by activityViewModels()
//
//
//    private lateinit var binding: FragmentImgDialogBinding
//    private lateinit var imgDitailUrl: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentImgDialogBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        init()
//    }
//
//    private fun init() {
//        viewModel.ditailImgLoadUrl.observe(viewLifecycleOwner){
//            Glide.with(requireContext())
//                .load(it)
//                .into(binding.iv)
//        }
//    }
//}