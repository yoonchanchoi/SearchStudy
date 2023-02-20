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
import com.example.searchstudy.network.models.response.AllItem
import com.example.searchstudy.ui.dialog.LoadingProgressDialog
import com.example.searchstudy.ui.recyclerview.view.ViewAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Constants
import com.example.searchstudy.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private var viewAdapter = ViewAdapter()
    private val tempViewItems = ArrayList<AllItem>()
    private val tempViewScrollItems = ArrayList<AllItem>()
    private var resCafeTotalCount = 0
    private var resBlogTotalCount = 0
    private var blogCount = 0
    private var cafeCount = 0
    private lateinit var binding: FragmentViewBinding
    private lateinit var progressBar: LoadingProgressDialog


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

//        일단 스크롤 제외
        binding.rvView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)?.let {
                        it.findLastCompletelyVisibleItemPosition()
                    }
                val itemTotalCount = recyclerView.adapter?.let {
                    it.itemCount
                } // 어댑터에 등록된 아이템의 총 개수 -1


//                Log.e("cyc","lastVisibleItemPosition-->${lastVisibleItemPosition}")
//                Log.e("cyc","itemTotalCount-->${itemTotalCount}")



                if (!binding.rvView.canScrollVertically(1) && lastVisibleItemPosition!! + 1 == itemTotalCount) {
                    Log.e("cyc", "---------------------------------------------------------------------------------")
                    Log.e("cyc", " ")
                    Log.e("cyc", "============================================================")
                    Log.e("cyc", "resBlogTotalCount-->${resBlogTotalCount}")
                    Log.e("cyc", "resCafeTotalCount-->${resCafeTotalCount}")
                    Log.e("cyc", "blogCount-->${blogCount}")
                    Log.e("cyc", "cafeCount-->${cafeCount}")
                    Log.e("cyc", "============================================================")
                    Log.e("cyc","itemTotalCount--->${itemTotalCount}")
                    Log.e("cyc","lastVisibleItemPosition--->${lastVisibleItemPosition+1}")
                    Log.e("cyc", "============================================================")
                    Log.e("cyc","스크롤-직전-----blogCount+1--->${blogCount+1}")
                    Log.e("cyc","스크롤-직전-----cafeCount+1--->${cafeCount+1}")

                    Log.e("cyc", " ")
                    Log.e("cyc", "---------------------------------------------------------------------------------")

                    tempViewScrollItems.clear()
                    if (resBlogTotalCount > blogCount && resCafeTotalCount > cafeCount) {
                        Log.e("cyc","1111111111111111111111111111")
                        viewModel.requestBlog(viewModel.query, blogCount + 1, true)
                        viewModel.viewMoreLoadState = Constants.VIEW_MORE_LOAD_BLOG_CAFE
                        progressBar.show()
                    } else if (resBlogTotalCount > blogCount && resCafeTotalCount <= cafeCount) {
                        Log.e("cyc","22222222222222222222222222222")
                        viewModel.requestBlog(viewModel.query, blogCount + 1, true)
                        viewModel.viewMoreLoadState = Constants.VIEW_MORE_LOAD_BLOG
                        progressBar.show()
                    } else if (resBlogTotalCount <= blogCount && resCafeTotalCount > cafeCount) {
                        Log.e("cyc","333333333333333333333333333333333")

                        viewModel.requestCafe(viewModel.query, cafeCount + 1, true)
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
        viewModel.blogResultSearchArraylist.observe(viewLifecycleOwner) {
            if (!viewModel.blogMoreLoad) {
                blogCount = 0
                tempViewItems.clear()
                resBlogTotalCount = it.total
                blogCount += it.allItems.size
                it.allItems.map { allItems -> allItems.type = Constants.ITEMS }
                tempViewItems.addAll(it.allItems)
            } else {
                Log.e("cyc", "")
                Log.e("cyc", "===========================시작=================================")
                for(i in it.allItems.indices){
                    Log.e("cyc", "it.allItems[$i]--->${it.allItems[i]}")
                }
                Log.e("cyc", "=============================끝===============================")
                Log.e("cyc", "")

                Log.e("cyc", "============================================================")
                Log.e("cyc", "it.allItems.size--->${it.allItems.size}")
                Log.e("cyc", "============================================================")

                when (viewModel.viewMoreLoadState) {
                    Constants.VIEW_MORE_LOAD_BLOG_CAFE -> {
                        blogCount += it.allItems.size
                        Log.e("cyc", "============================================================")
                        Log.e("cyc", "blogCount->${blogCount}")
                        Log.e("cyc", "============================================================")

                        tempViewScrollItems.addAll(it.allItems)
                        viewModel.requestCafe(viewModel.query, cafeCount + 1, true)
                    }
                    Constants.VIEW_MORE_LOAD_BLOG -> {
                        blogCount += it.allItems.size
                        tempViewScrollItems.addAll(it.allItems)
                        Util.dataSort(tempViewScrollItems)
                        viewAdapter.addData(tempViewScrollItems)
                        progressBar.dismiss()

                    }
                }
            }
        }
        viewModel.cafeResultSearchArraylist.observe(viewLifecycleOwner) {
            if (!viewModel.cafeMoreLoad) {
                cafeCount = 0
                resCafeTotalCount = it.total
                cafeCount += it.allItems.size
                it.allItems.map { allItems -> allItems.type = Constants.ITEMS }
                tempViewItems.addAll(it.allItems)
                Util.dataSort(tempViewItems)
                viewAdapter.setData(tempViewItems)
            } else {
                cafeCount += it.allItems.size
                tempViewScrollItems.addAll(it.allItems)
                Util.dataSort(tempViewScrollItems)
                viewAdapter.addData(tempViewScrollItems)
                progressBar.dismiss()

            }
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




















//                if (resBlogTotalCount > blogCount || resCafeTotalCount > cafeCount) {
//
//                    if (!binding.rvView.canScrollVertically(1) && lastVisibleItemPosition!! + 1 == itemTotalCount) {
//                        tempViewScrollItems.clear()
//                        if(resBlogTotalCount > blogCount){
//
//                        }else{
//
////                    Log.e("cyc"," ")
////                    Log.e("cyc","============================================================")
////                    Log.e("cyc","resBlogTotalCount-->${resBlogTotalCount}")
////                    Log.e("cyc","resCafeTotalCount-->${resCafeTotalCount}")
////                    Log.e("cyc","blogCount-->${blogCount}")
////                    Log.e("cyc","cafeCount-->${cafeCount}")
////                    Log.e("cyc","============================================================")
////                    Log.e("cyc"," ")
//
////                        viewModel.requestBlog(viewModel.query, blogCount + 1, true)
////                        if (resBlogTotalCount > blogCount && resCafeTotalCount > cafeCount) {
////                            viewModel.viewMoreLoadState = Constants.VIEW_MORE_LOAD_BLOG_CAFE
////                            progressBar.show()
////                        } else if (resBlogTotalCount > blogCount && resCafeTotalCount <= cafeCount) {
////                            viewModel.viewMoreLoadState = Constants.VIEW_MORE_LOAD_BLOG
////                            progressBar.show()
////                        } else if (resBlogTotalCount <= blogCount && resCafeTotalCount > cafeCount) {
////                            viewModel.viewMoreLoadState = Constants.VIEW_MORE_LOAD_CAFE
////                            progressBar.show()
//                        }
//                    }
//                }









