package com.example.searchstudy.ui.recyclerview.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.searchstudy.ui.fragment.AllFragment
import com.example.searchstudy.ui.fragment.DictionaryFragment
import com.example.searchstudy.ui.fragment.ImgFragment
import com.example.searchstudy.ui.fragment.ViewFragment
import com.google.android.material.tabs.TabLayout
import kotlin.jvm.internal.Ref

class ViewpagerFragmentAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){

//    private val fragmentList = listOf(AllFragment(), ViewFragment(), DictionaryFragment(), ImgFragment())
    private val fragmentList = arrayListOf<Fragment>()
    private val titleList = arrayListOf<String>()


    /**
     * 뷰페이저 프래그먼튼 갯수 가져오기
     */
    override fun getItemCount(): Int {
        return fragmentList.size
    }
    /**
     * 해당 포지션의 프래그먼트 가져오기
     */

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
    fun addFragment(title: String, frag : Fragment){
        fragmentList.add(frag)
        titleList.add(title)
    }
    fun firstAddFragment(title: String, frg: Fragment){
        fragmentList.add(0,frg)
        titleList.add(0,title)
    }
    fun lastAddFragment(title: String, frg: Fragment){
        if(fragmentList.size==0){
            fragmentList.add(frg)
        }else{
            fragmentList.add(fragmentList.size-1,frg)
        }
        if(fragmentList.size==0){
            titleList.add(title)

        }else{
            titleList.add(fragmentList.size-1,title)
        }
    }
    fun getTitleList(position: Int): String{
        return titleList[position]
    }
}
