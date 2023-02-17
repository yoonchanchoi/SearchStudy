package com.example.searchstudy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.FragmentDictionaryBinding
import com.example.searchstudy.network.models.dto.integrated.Integrated
import com.example.searchstudy.network.models.response.AllItems
import com.example.searchstudy.ui.dialog.LoadingProgressDialog
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentDictionaryBinding
    private lateinit var dictionaryAdapter: DictionaryAdapter
    private val tempDicItems = ArrayList<AllItems>()
    private var resDicTotal = 0
    private lateinit var progressBar: LoadingProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    /**
     * 백과사전 데이터 세팅
     */
    private fun init() {
        settingAdapter()
        initObserve()
        progressBar= LoadingProgressDialog(requireContext())
        binding.rvDictionary.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)?.let {
                        it.findLastCompletelyVisibleItemPosition()+1
                    }
                val itemTotalCount = recyclerView.adapter?.let {
                    it.itemCount
                } // 어댑터에 등록된 아이템의 총 개수 -1
                // 스크롤이 끝에 도달했는지 확인
//                Log.e("cyc", "")
//                Log.e("cyc", "로그를 위해서=====itemTotalCount-->${itemTotalCount}")
//                Log.e("cyc", "로그를 위해서=====lastVisibleItemPosition-->${lastVisibleItemPosition}")
//                Log.e("cyc", "로그를 위해서=====resDicTotal-->${resDicTotal}")
//                Log.e("cyc", "")

                if(resDicTotal>itemTotalCount!!){
                    if (!binding.rvDictionary.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                        viewModel.requestDictionary(viewModel.query, itemTotalCount!! + 1, true)
                        viewModel.lastItemPoint = itemTotalCount + 1

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
        viewModel.dictionaryItemsArraylist.observe(viewLifecycleOwner) {
            resDicTotal=it.total
            Util.dataSort(it.allItems)
            val dictionaryIntegrated = (Integrated(allItemsarraylist = it.allItems))
            viewModel.setDicIntegrateditems(dictionaryIntegrated)
//            if (!viewModel.dicMoreLoad) {
//                tempDicItems.clear()
//            }
//            Util.dataSort(it)
////            Log.e("cyc", "")
////            Log.e("cyc","[---------viewModel.lastItemPoint---------]->${viewModel.lastItemPoint}")
////            Log.e("cyc", "")
////            Log.e("cyc", "--------dic--각각의=한번=호출=아이템=시작---------")
////            for (i in 0 until  it.size) {
////                Log.e("cyc", "tempDicItems---->${it[i]}")
////            }
////            Log.e("cyc", "--------dic==각각의=한번=호출=끝---------")
////            Log.e("cyc", "")
//            tempDicItems.addAll(it)
//            //여기 임시 수정됨
////            Util.dataSort(tempDicItems)
////            Log.e("cyc", "")
////            Log.e("cyc","[---------viewModel.lastItemPoint---------]->${viewModel.lastItemPoint}")
////            Log.e("cyc", "")
////            Log.e("cyc", "--------dic--tempDicItems-시작---------")
////            for (i in 0 until tempDicItems.size) {
////                Log.e("cyc", "tempDicItems---->${tempDicItems[i]}")
////            }
////            Log.e("cyc", "--------dic--tempDicItems-끝---------")
////            Log.e("cyc", "")
//            val dictionaryIntegrated = (Integrated(allItemsarraylist = tempDicItems))
//            viewModel.setDicIntegrateditems(dictionaryIntegrated)
        }
        viewModel.dictionaryIntegrated.observe(viewLifecycleOwner) {

            if (!viewModel.dicMoreLoad) {
                dictionaryAdapter.setData(it)
            }else{
                dictionaryAdapter.addData(it)
            }
//            dictionaryAdapter.setData(it)
            progressBar.dismiss()
        }
    }

    /**
     * 어댑터 세팅
     */
    private fun settingAdapter() {
        dictionaryAdapter = DictionaryAdapter()
        val dictionaryLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        dictionaryLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rvDictionary.apply {
            layoutManager = dictionaryLayoutManager
            adapter = dictionaryAdapter

        }
    }
}