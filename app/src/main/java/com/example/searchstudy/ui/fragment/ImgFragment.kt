package com.example.searchstudy.ui.fragment

import android.os.Bundle
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
    private lateinit var binding: FragmentImgBinding
    private lateinit var imgAdapter: ImgAdapter
    private val tempImgItems = ArrayList<ImgItems>()
    private var resImgTotal = 0
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


    /**
     *
     */
    private fun init() {
        settingAdapter()
        initObserve()
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


                if(resImgTotal>itemTotalCount!!){
                    if (binding.rvImg.canScrollVertically(1) && lastVisibleItemPosition + 1 == itemTotalCount) {
                        viewModel.requestImg(viewModel.query, itemTotalCount+1, true)
                        viewModel.lastImgItemPoint = itemTotalCount
                        progressBar.show()
                    }
                }
            }
        })
    }

    private fun initObserve() {
        viewModel.imgItemsArraylist.observe(viewLifecycleOwner) {
            if (!viewModel.imgMoreLoad) {
//                tempImgItems.clear()
//                tempImgItems.addAll(it)
                imgAdapter.setData(it)
            }else{
                imgAdapter.addData(it)

            }
            progressBar.dismiss()
        }
        viewModel.imgTotalItems.observe(viewLifecycleOwner) {
            resImgTotal = it
        }
    }

    /**
     * 어댑터 세팅
     */
    private fun settingAdapter() {
        imgAdapter = ImgAdapter()
        val imgLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            }
        binding.rvImg.apply {
            layoutManager = imgLayoutManager
            adapter = imgAdapter
            smoothScrollBy(1,100)
        }
    }

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

