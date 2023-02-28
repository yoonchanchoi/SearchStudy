package com.example.searchstudy.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.searchstudy.R
import com.example.searchstudy.databinding.FragmentAdultWarningDialogBinding
import com.example.searchstudy.databinding.FragmentImgBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdultWarningDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentAdultWarningDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdultWarningDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCheck.setOnClickListener {
            dismiss()
        }
    }
}