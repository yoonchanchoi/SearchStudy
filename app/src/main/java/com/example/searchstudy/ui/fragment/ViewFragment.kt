package com.example.searchstudy.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.FragmentViewBinding
import com.example.searchstudy.network.models.dto.integrated.Integrated
import com.example.searchstudy.network.models.response.AllItems
import com.example.searchstudy.ui.dialog.LoadingProgressDialog
import com.example.searchstudy.ui.recyclerview.view.AllAdapterItemViewViewHolder
import com.example.searchstudy.ui.recyclerview.view.ViewAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentViewBinding
    private var viewAdapter = ViewAdapter()
    private val tempViewItems = ArrayList<AllItems>()
    private val tempTotalViewitems = ArrayList<AllItems>()
    private lateinit var progressBar: LoadingProgressDialog
    private var resCafeTotal = 0
    private var resBlogTotal = 0
    private var blogCount = 0
    private var cafeCount = 0


    //아이템 갯수를 체크 각각의 오브절부에서

//    private val progressBar = LoadingProgressDialog(requireContext())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    /**
     * view 데이터 세팅
     */
    private fun init() {
        viewAdapterSetting()
        initObserve()
        progressBar = LoadingProgressDialog(requireContext())
        binding.rvView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                Log.e("cyc", "onScrolled")
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)?.let {
                        it.findLastCompletelyVisibleItemPosition()
                    }
                Log.e("cyc","lastVisibleItemPosition--->${lastVisibleItemPosition}")

                val itemTotalCount = recyclerView.adapter?.let {
                    Log.e("cyc","it--->${it}")
                    it.itemCount
                } // 어댑터에 등록된 아이템의 총 개수 -1
                Log.e("cyc","itemTotalCount--->${itemTotalCount}")
                // 스크롤이 끝에 도달했는지 확인
                Log.e("cyc", "스크롤 다음 ")
                if (resBlogTotal > blogCount || resCafeTotal > cafeCount) {
//                    tempTotalViewitems.clear()
                    Log.e("cyc", "1111111111")
                    if (!binding.rvView.canScrollVertically(1) && lastVisibleItemPosition!!+1 == itemTotalCount) {
                        Log.e("cyc", "스크롤 갯수")
                        if (resBlogTotal > blogCount && resCafeTotal > cafeCount) {
                            viewModel.requestCafe(viewModel.query, cafeCount + 1, true)
                            viewModel.requestBlog(viewModel.query, blogCount + 1, true)
                        } else if (resBlogTotal > blogCount) {
                            viewModel.requestBlog(viewModel.query, blogCount + 1, true)
                        } else {
                            viewModel.requestCafe(viewModel.query, cafeCount + 1, true)
                        }
//                        tempTotalViewitems.clear()
                        progressBar.show()
                    }
                }
            }
        })

    }

    /**
     * 옵저버 세팅
     */
    private fun initObserve() {
        viewModel.blogItemsArraylist.observe(viewLifecycleOwner) {
            Log.e("cyc", "viewfragment___initObserve()_____blogItemsArrayList")
            if (!viewModel.blogMoreLoad) {
                blogCount = 0
                tempTotalViewitems.clear()
                resBlogTotal = it.total
            }
            Log.e("cyc", "2222222222")

            blogCount += it.allItems.size
            tempTotalViewitems.addAll(it.allItems)
            Util.dataSort(tempTotalViewitems)
//            tempViewItems.addAll(it.allItems)
//            Log.e("cyc", "")
//            Log.e("cyc", "--------view-bolg-tempViewitems-시작---------")
//            for (i in 0 until tempTotalViewitems.size) {
//                Log.e("cyc", "tempTotalViewitems---->${tempTotalViewitems[i]}")
//            }
//            Log.e("cyc", "--------view-bolg-tempViewitems-끝---------")
//            Log.e("cyc", "")
        }
        viewModel.cafeItemsArraylist.observe(viewLifecycleOwner) {
            if (!viewModel.cafeMoreLoad) {
                cafeCount = 0
                resCafeTotal = it.total
            }
            cafeCount += it.allItems.size
            tempTotalViewitems.addAll(it.allItems)
            Util.dataSort(tempTotalViewitems)
//            resCafeTotal = it.total
//            tempViewItems.addAll(it.allItems)
//            //여기 임시 수정
//            Util.dataSort(tempViewItems)
//            tempTotalViewitems.addAll(tempViewItems)

            Log.e("cyc", "")
            Log.e("cyc", "--------view-블로그 + 카페-tempViewitems-시작---------")
            for (i in 0 until tempTotalViewitems.size) {
                Log.e("cyc", "tempTotalViewitems---->${tempTotalViewitems[i]}")
            }
            Log.e("cyc", "--------view-블로그 + 카페-tempViewitems-끝---------")
            Log.e("cyc", "")
            val viewIntegrated = (Integrated(allItemsarraylist = tempTotalViewitems))
            viewModel.setViewIntegrateditems(viewIntegrated)
        }
        viewModel.viewIntegrated.observe(viewLifecycleOwner) {

            if (!viewModel.dicMoreLoad && !viewModel.cafeMoreLoad) {
                Log.e("cyc", "viewadapter___setData")

                viewAdapter.setData(it)
            } else {
                Log.e("cyc", "viewadapter___addData")
                viewAdapter.addData(it)
            }
//            Log.e("cyc", "")
//            Log.e("cyc", "--------view-블로그 + 카페-어탭터-tempViewitems-시작---------")
//            for (i in 0 until it.allItemsarraylist!!.size) {
//                Log.e("cyc", "tempViewitems---->${it.allItemsarraylist!![i]}")
//            }
//            Log.e("cyc", "--------view-블로그 + 카페-어탭터-tempViewitems-끝---------")
//            Log.e("cyc", "")
            progressBar.dismiss()
        }
    }


    /**
     * 어댑터 세팅
     */
    private fun viewAdapterSetting() {
        viewAdapter = ViewAdapter()
        val rvViewLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvView.apply {
            layoutManager = rvViewLayoutManager
            adapter = viewAdapter
        }
    }
}











