package com.example.searchstudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.searchstudy.databinding.FragmentImgBinding
import com.example.searchstudy.network.models.response.ImgItems
import com.example.searchstudy.ui.dialog.LoadingProgressDialog
import com.example.searchstudy.ui.recyclerview.img.ImgAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImgFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private var resImgTotalCount = 0
    private lateinit var binding: FragmentImgBinding
    private lateinit var imgAdapter: ImgAdapter
    private lateinit var progressBar: LoadingProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImgBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        settingAdapter()
        initObserve()

        //스크롤 세팅
        progressBar = LoadingProgressDialog(requireContext())
        binding.rvImg.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val itemTotalCount = recyclerView.layoutManager?.let {
                    it.itemCount
                }
                val lastVisibleItemPositions =recyclerView.layoutManager?.let {
                    (it as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                }
                val lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions!!);




//                Log.e("cyc","이미지----itemTotalCount--->${itemTotalCount}")
//                Log.e("cyc","이미지----resImgTotalCount--->${resImgTotalCount}")

                if(resImgTotalCount>itemTotalCount!!){
                    if (binding.rvImg.canScrollVertically(1) && lastVisibleItemPosition + 1 == itemTotalCount) {
                        viewModel.requestImg(viewModel.query, itemTotalCount+1, true)
                        viewModel.lastImgItemPoint = itemTotalCount
                        progressBar.show()
                    }
                }
            }
        })
    }
    /**
     * 옵저버세팅
     */
    private fun initObserve() {
        viewModel.imgResultSearchArraylist.observe(viewLifecycleOwner) {
            resImgTotalCount = it.total

            if (!viewModel.imgMoreLoad) {
//                Log.e("cyc","====================검색=시작==========================")
//                for(i in it.imgItems.indices){
//                    Log.e("cyc","it.imgItems[i]--->${it.imgItems[i]}")
//                }
//                Log.e("cyc","====================검색=끝==========================")
                imgAdapter.setData(it.imgItems)


            }else{
//                Log.e("cyc","====================더보기=시작==========================")
//                for(i in it.imgItems.indices){
//                    Log.e("cyc","it.imgItems[i]--->${it.imgItems[i]}")
//                }
//                Log.e("cyc","====================더보기=끝==========================")
                imgAdapter.addData(it.imgItems)

            }
            progressBar.dismiss()
        }
    }

    /**
     * 어댑터 세팅
     */
    private fun settingAdapter() {
        imgAdapter = ImgAdapter()
        val imgLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
                //아이템간의 마진의 따른 이상한 배치 방지
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            }
        binding.rvImg.apply {
            layoutManager = imgLayoutManager
            adapter = imgAdapter
            smoothScrollBy(1,100)
        }
    }

    /**
     * 스크롤시 현재 보여지는 이미지 아이템 갯수 반환
     */
    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }
}

