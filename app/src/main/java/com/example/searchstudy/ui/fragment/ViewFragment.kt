package com.example.searchstudy.ui.fragment


import android.content.Context
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
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)?.let {
                        it.findLastCompletelyVisibleItemPosition()
                    }
                val itemTotalCount = recyclerView.adapter?.let {
                    it.itemCount - 1
                } // 어댑터에 등록된 아이템의 총 개수 -1

                Log.e(">>>>>>>>>>>>>>>>>>>>>>>", "TotalCount :: $itemTotalCount")
                // 스크롤이 끝에 도달했는지 확인
                if (!binding.rvView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    viewModel.requestBlog(viewModel.query, (itemTotalCount!! + 1)/2, true)
                    viewModel.lastViewItemPoint = (itemTotalCount + 1)/2
//                    binding.clProgress.visibility=View.VISIBLE
                    progressBar.show()
                }
            }
        })

    }

    /**
     * 옵저버 세팅
     */
    private fun initObserve() {
        viewModel.blogItemsArraylist.observe(viewLifecycleOwner) {
            Log.e("cyc","viewfragment___initObserve()_____blogItemsArrayList")

            tempViewItems.clear()
            if (!viewModel.viewMoreLoad) {
                tempTotalViewitems.clear()
            }
            tempViewItems.addAll(it)
//            Log.e("cyc", "")
//            Log.e("cyc", "--------view-bolg-tempViewitems-시작---------")
//            for (i in 0 until tempTotalViewitems.size) {
//                Log.e("cyc", "tempTotalViewitems---->${tempTotalViewitems[i]}")
//            }
//            Log.e("cyc", "--------view-bolg-tempViewitems-끝---------")
//            Log.e("cyc", "")
        }
        viewModel.cafeItemsArraylist.observe(viewLifecycleOwner) {
            Log.e("cyc","viewfragment___initObserve()_____cafeItemsArrayList")

            tempViewItems.addAll(it)
            //여기 임시 수정
            Util.dataSort(tempViewItems)
            tempTotalViewitems.addAll(tempViewItems)

//            Log.e("cyc", "")
//            Log.e("cyc", "--------view-블로그 + 카페-tempViewitems-시작---------")
//            for (i in 0 until tempTotalViewitems.size) {
//                Log.e("cyc", "tempTotalViewitems---->${tempTotalViewitems[i]}")
//            }
//            Log.e("cyc", "--------view-블로그 + 카페-tempViewitems-끝---------")
//            Log.e("cyc", "")
            val viewIntegrated = (Integrated(allItemsarraylist = tempTotalViewitems))
            viewModel.setViewIntegrateditems(viewIntegrated)
        }
        viewModel.viewIntegrated.observe(viewLifecycleOwner) {
//            Log.e("cyc", "")
//            Log.e("cyc", "--------view-블로그 + 카페-어탭터-tempViewitems-시작---------")
//            for (i in 0 until it.allItemsarraylist!!.size) {
//                Log.e("cyc", "tempViewitems---->${it.allItemsarraylist!![i]}")
//            }
//            Log.e("cyc", "--------view-블로그 + 카페-어탭터-tempViewitems-끝---------")
//            Log.e("cyc", "")
            viewAdapter.setData(it)
            progressBar.dismiss()
//            binding.clProgress.visibility=View.INVISIBLE
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


//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//
//                if (newState == SCROLL_STATE_SETTLING) {
//                    Log.e("cyc", "스크롤 끝")
//
//                }
//            }


// 화면에 보이는 마지막 아이템의 position
//                (recyclerView.layoutManager as LinearLayoutManager).let {
//                    lastVisibleItemPosition = it.findLastCompletelyVisibleItemPosition()
//                }
//
//                recyclerView.adapter?.let {
//                    itemTotalCount = it.itemCount - 1
//
//                } // 어댑터에 등록된 아이템의 총 개수 -1
//
//                Log.e("cyc", "lastVisibleItemPosition--->${lastVisibleItemPosition}")
//                Log.e("cyc", "itemTotalCount--->${itemTotalCount}")
//
//                // 스크롤이 끝에 도달했는지 확인
//                if (binding.rvView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
////                        viewModel.requestBlog(viewModel.query)
//                    Log.e("cyc", "스크롤 끝")
//
//                }

//                if (dy > 0) {
//                    // 화면에 보이는 마지막 아이템의 position
//                    (recyclerView.layoutManager as LinearLayoutManager?)?.let {
//                        lastVisibleItemPosition = it.findLastCompletelyVisibleItemPosition()
//                    }
//
//                    recyclerView.adapter?.let {
//                        itemTotalCount = it.itemCount - 1
//
//                    } // 어댑터에 등록된 아이템의 총 개수 -1
//
//                    Log.e("cyc", "lastVisibleItemPosition--->${lastVisibleItemPosition}")
//                    Log.e("cyc", "itemTotalCount--->${itemTotalCount}")
//
//                    // 스크롤이 끝에 도달했는지 확인
//                    if (binding.rvView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
////                        viewModel.requestBlog(viewModel.query)
//                        Log.e("cyc", "스크롤 끝")
//
//                    }
//                }

//                val lastVisibleItemPosition =
//                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
//                recyclerView.adapter?.let {
//                    val itemTotalCount = it.itemCount - 1
//                    if(itemTotalCount == lastVisibleItemPosition){
////                        binding.pbLoad.progress
////                        //데이터 api 호출
////                        //데이터 세팅
////                        //프로그래스마 종료(안보여주기)
//
//                        binding.pbLoad.progress
//                        viewModel.requestBlog(viewModel.query)
//                    }
//                }












//if (!viewModel.viewMoreLoad) {
//    tempTotalViewitems.clear()
//    tempViewItems.addAll(it)
//
//}else{
//    tempViewItems.addAll(it)
//    viewModel.requestCafe(viewModel.query,viewModel.lastViewItemPoint)
//}
