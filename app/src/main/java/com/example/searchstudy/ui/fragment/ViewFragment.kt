package com.example.searchstudy.ui.fragment


import android.content.Intent
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
import com.example.searchstudy.ui.activity.WebViewActivity
import com.example.searchstudy.ui.dialog.LoadingProgressDialog
import com.example.searchstudy.ui.recyclerview.view.ViewAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Constants
import com.example.searchstudy.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewFragment : Fragment() {

    companion object {
        private const val PAGE_COUNT = 10
    }

    private val viewModel: MainActivityViewModel by activityViewModels()

    private lateinit var binding: FragmentViewBinding

    private lateinit var progressBar: LoadingProgressDialog

    private lateinit var viewAdapter: ViewAdapter

    private val tempViewItems = ArrayList<AllItem>()

    private val tempViewScrollItems = ArrayList<AllItem>()

    private var resCafeTotalCount = 0 //카페 api의 총 반환 갯수

    private var resBlogTotalCount = 0 //블로그 api의 총 반환 갯수

    private var blogCount = 0   // 누적 블로그 아이템 갯수

    private var cafeCount = 0   // 누적 카페 아이템 갯수


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

        viewAdapter.setItemClickListener(object : ViewAdapter.ViewItemRecyclerListener{
            override fun onItemClick(link:String) {
                val intent = Intent(requireContext(), WebViewActivity::class.java)
                intent.putExtra(Constants.DITAIL_WEB_LOAD_URL,link)
                startActivity(intent)
            }
        })

//        일단 스크롤 제외
        binding.rvView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                // 어댑터에 등록된 아이템의 총 개수 -1
                val itemTotalCount = recyclerView.adapter?.let {
                    it.itemCount
                }

                if (!binding.rvView.canScrollVertically(1) && lastVisibleItemPosition + 1 == itemTotalCount) {
                    var display = PAGE_COUNT

                    // api의 반환 결과 값이 총갯수를 넘어갈때 원래는 해당 남은 갯수만 가져와야 되지만
                    // 해당 api 오류로 인한 다른 값으로 변경되어 호출되므로
                    // 강제적으로 해당 총 반환값에 직전에 추가 호출시 호출(display)값을 지정해주기 위해서 바로 아래 if를 작성
                    if (blogCount + PAGE_COUNT > resBlogTotalCount) {
                        display = resBlogTotalCount - blogCount - 1
                    }

                    tempViewScrollItems.clear()
                    // 해당 총 갯수 반환을 넘어갈 때 생기는 오류로 인해서 그 총갯수 직전 -1 갯수까지 반환하기 위한 count
                    val count = resBlogTotalCount - 1

                    if (count > blogCount && resCafeTotalCount > cafeCount) {
                        viewModel.requestBlog(
                            viewModel.query,
                            display = display,
                            start = blogCount + 1,
                            checkMoreLoad = true
                        )
                        viewModel.viewMoreLoadState = Constants.VIEW_MORE_LOAD_BLOG_CAFE
                        progressBar.show()
                    } else if (count > blogCount && resCafeTotalCount <= cafeCount) {
                        viewModel.requestBlog(
                            viewModel.query,
                            display = display,
                            start = blogCount + 1,
                            checkMoreLoad = true
                        )
                        viewModel.viewMoreLoadState = Constants.VIEW_MORE_LOAD_BLOG
                        progressBar.show()
                    } else if (count <= blogCount && resCafeTotalCount > cafeCount) {
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
                tempViewItems.clear()
                resBlogTotalCount = it.total
                blogCount = it.allItems.size
//                it.allItems.map { allItems -> allItems.type = Constants.ITEMS }
//                tempViewItems.addAll(it.allItems)
            } else {
                when (viewModel.viewMoreLoadState) {
                    Constants.VIEW_MORE_LOAD_BLOG_CAFE -> {
                        blogCount += it.allItems.size
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
                resCafeTotalCount = it.total
                cafeCount = it.allItems.size
//                it.allItems.map { allItems -> allItems.type = Constants.ITEMS }
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
        viewAdapter = ViewAdapter(requireContext())
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









