package com.example.searchstudy.ui.fragment

import android.R
import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.searchstudy.databinding.DialogProgressbarBinding
import com.google.android.material.progressindicator.IndeterminateDrawable


//class LoadingProgressDialog(context: Context) : Dialog(context) {
//
//    private var binding: DialogProgressBinding =
//        DialogProgressBinding.inflate(layoutInflater)
//
//    init {
//        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        setCanceledOnTouchOutside(false)
//        setContentView(binding.root)
//    }
//
//
//}


//class LoadingProgressDialog(context: Context) : Dialog(context) {
//
//    private var binding: DialogProgressBinding = DialogProgressBinding.inflate(layoutInflater)
//
//    init {
//        setContentView(binding.root)
//        setCancelable(false)
//    }
//
//}


class LoadingProgressDialog(context: Context) : Dialog(context) {

    private var binding: DialogProgressbarBinding= DialogProgressbarBinding.inflate(layoutInflater)


    init {
        // 다이얼 로그 제목을 안보이게...
        binding.pb.isIndeterminate = true
        binding.pb.indeterminateDrawable.setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.MULTIPLY)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

    }



}