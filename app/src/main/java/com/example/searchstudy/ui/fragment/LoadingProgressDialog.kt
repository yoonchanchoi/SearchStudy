package com.example.searchstudy.ui.fragment

import android.R
import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.searchstudy.databinding.DialogProgressbarBinding


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
//        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        setContentView(binding.root)
//        setCancelable(false)
//    }
//
//}


class LoadingProgressDialog(context: Context) : Dialog(context) {

    private var binding: DialogProgressbarBinding= DialogProgressbarBinding.inflate(layoutInflater)

    init {
        // 다이얼 로그 제목을 안보이게...
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
    }
}