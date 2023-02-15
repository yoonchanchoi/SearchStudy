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
import com.example.searchstudy.ui.recyclerview.img.ImgAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImgFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentImgBinding
    private lateinit var imgAdapter: ImgAdapter
    private val tempImgItems = ArrayList<ImgItems>()
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
                val visibleItemCount = recyclerView.layoutManager?.let {
                    it.childCount
                }
                val totalItemCount = recyclerView.layoutManager?.let {
                    it.itemCount
                }
                var pastVisibleItems = 0
//                var firstVisibleItems: IntArray? = null
                val firstVisibleItems =
                    (recyclerView.layoutManager as StaggeredGridLayoutManager).findFirstVisibleItemPositions(
                        null
                    )
//
//                Log.e("cyc","imgfrag----visibleItemCount---${visibleItemCount}")
//                Log.e("cyc","imgfrag----totalItemCount---${totalItemCount}")

//                firstVisibleItems = mLayoutManager.findFirstVisibleItemPositions(firstVisibleItems)
                if (firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
                    pastVisibleItems = firstVisibleItems[0]
                }

                if (visibleItemCount!! + pastVisibleItems >= totalItemCount!!) {
                    viewModel.requestImg(viewModel.query, totalItemCount, true)
                    viewModel.lastImgItemPoint = totalItemCount
                    progressBar.show()
                }
//                val lastVisibleItemPosition =
//                    (recyclerView.layoutManager as StaggeredGridLayoutManager?)?.let {
//                        it.findLastCompletelyVisibleItemPositions(null)
////                        findLastCompletelyVisibleItemPosition
//                    }
//                val itemTotalCount = recyclerView.adapter?.let {
//                    it.itemCount - 1
//                } // 어댑터에 등록된 아이템의 총 개수 -1
//                // 스크롤이 끝에 도달했는지 확인
//                if (!binding.rvImg.canScrollVertically(1) && lastVisibleItemPosition!!.size == itemTotalCount) {
//                    viewModel.requestImg(viewModel.query, itemTotalCount!! + 1, true)
//                    viewModel.lastItemPoint = itemTotalCount + 1
//                    progressBar.show()
//                }
            }
        })
    }


    private fun initObserve() {
        viewModel.imgItemsArraylist.observe(viewLifecycleOwner) {
            Log.e("cyc","imgfragment___initObserve()_____imgItemsArrayList")

            if (!viewModel.imgMoreLoad) {
                tempImgItems.clear()
            }
            tempImgItems.addAll(it)
            imgAdapter.setData(tempImgItems)
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
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            }
        binding.rvImg.apply {
            layoutManager = imgLayoutManager
            adapter = imgAdapter
        }
    }
}