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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentImgDialogBinding.inflate(inflater, container, false)
        arguments?.let {
            imgDitailUrl = it.getString(Constants.DITAIL_IMG_LOAD_URL).toString()
            Log.e("cyc","imgDitailUrl--->${imgDitailUrl}")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

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