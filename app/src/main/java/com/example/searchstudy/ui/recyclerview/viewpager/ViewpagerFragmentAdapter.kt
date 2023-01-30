package com.example.searchstudy.ui.recyclerview.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.searchstudy.ui.fragment.AllFragment
import com.example.searchstudy.ui.fragment.DictionaryFragment
import com.example.searchstudy.ui.fragment.ImgFragment
import com.example.searchstudy.ui.fragment.ViewFragment

class ViewpagerFragmentAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){

    private val fragmentList = listOf(AllFragment(), ViewFragment(), DictionaryFragment(), ImgFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}