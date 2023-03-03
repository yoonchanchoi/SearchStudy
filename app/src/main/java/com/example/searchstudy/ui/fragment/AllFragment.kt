package com.example.searchstudy.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.R
import com.example.searchstudy.databinding.FragmentAllBinding
//import com.example.searchstudy.network.models.dto.searchDto.Papago.PapagoResult
import com.example.searchstudy.network.models.response.AllItem
import com.example.searchstudy.network.models.response.ResultNaver
import com.example.searchstudy.network.models.response.ResultPapago
import com.example.searchstudy.network.models.response.ResultSearchAll
import com.example.searchstudy.ui.activity.WebViewActivity
import com.example.searchstudy.ui.recyclerview.all.AllItemsAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Constants
import com.example.searchstudy.util.Util
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import org.xml.sax.Parser
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@AndroidEntryPoint
class AllFragment : Fragment() {
//class AllFragment : Fragment() {

    private lateinit var binding: FragmentAllBinding

    private lateinit var allItemsAdapter: AllItemsAdapter

    private val viewModel: MainActivityViewModel by activityViewModels()

    private val tempResultSearchAll = ArrayList<ResultNaver>()// 임시 통합 아이템

    private val tempAllViewItems = ArrayList<AllItem>() // 임시 VEIW 아이템

    private var allFlag = false

    private var papagoFlag = false

//


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        adapterSetting()
        initObserve()

//        allAdapter.set
    }

    /**
     * 옵저버 세팅
     */
    private fun initObserve() {
        viewModel.blogResultSearchArraylist.observe(viewLifecycleOwner) {
            if (!viewModel.blogMoreLoad) {
                allFlag = false
                papagoFlag = false
                tempAllViewItems.clear()
                tempResultSearchAll.clear()
                it.allItems.map { allItems ->
                    allItems.type = Constants.VIEW
                }
                tempAllViewItems.addAll(it.allItems)
            }
        }
        viewModel.cafeResultSearchArraylist.observe(viewLifecycleOwner) {

            if (it.allItems.size > 0) {
                if (!viewModel.cafeMoreLoad) {

                    it.allItems.map { allItems ->
                        allItems.type = Constants.VIEW
                    }

                    tempAllViewItems.addAll(it.allItems)
                    Util.dataSort(tempAllViewItems)
                    tempResultSearchAll.add(
                        ResultSearchAll(
                            category = getString(R.string.tab_title_view),
                            allItems = Util.dataExtraction(tempAllViewItems)
                        )
                    )
                }
            }
        }
        /**
         * 옵저버 세팅
         */
        viewModel.dictionaryResultSearchArraylist.observe(viewLifecycleOwner) {


            if (!viewModel.dicMoreLoad) {

//                Log.e("cyc", "ResultSearch---it---->${it}")

                it.allItems.map { allItems ->
                    allItems.type = Constants.DICTIONARY
                }
                if (it.allItems.size > 0) {
                    Util.dataSort(it.allItems)
                    tempResultSearchAll.add(
                        ResultSearchAll(
                            category = getString(R.string.tab_title_dictionary),
                            allItems = Util.dataExtraction(it.allItems)

                        )
                    )
                    allFlag = true
                }
                if(allFlag&&papagoFlag){
                    allItemsAdapter.setData(tempResultSearchAll)
                }
            }
        }
        viewModel.papagoResult.observe(viewLifecycleOwner) {
            Log.e("cyc","---------------------------papagoResult---------------------------")
            it.classType = Constants.TRANSLATION
            it.originText = viewModel.query.replace(getString(R.string.translation), "").trim()
            tempResultSearchAll.add(0,it)
            papagoFlag=true

            if(allFlag&&papagoFlag){
                allItemsAdapter.setData(tempResultSearchAll)
            }
//            allItemsAdapter.addData(it)

        }
//        viewModel.papagoResultToString.observe(viewLifecycleOwner) {
//
//            val map = Gson().fromJson(it, Map::class.java)
//            Log.e("cyc", "map-->${map}")
//            Log.e("cyc", " map.keys-->${map.keys}")
//            Log.e("cyc", " map[message]-->${map["message"]}")
//            val message3 = Gson().fromJson(map["message"].toString(), Map::class.java)
////            Log.e("cyc", " message3-->${message3}")
////            message3["result"]
////            Log.e("cyc", "message3[result]-->${message3["result"]}")
//            val temp = message3["result"] as LinkedTreeMap<*, *>
////            Log.e("cyc", "temp--->${temp}")
////            Log.e("cyc", "temp_key_srcLangType-->${temp["srcLangType"]}")
//
//            val result4 = Gson().fromJson(message3["result"].toString(), PapagoResult::class.java)
//            Log.e("cyc", "result4--> $result4")
//
//
//            Log.e("cyc", "temp_key_tarLangType-->${temp["tarLangType"]}")
//            Log.e("cyc", "temp_key_translatedText-->${temp["translatedText"]}")
//            Log.e("cyc", "temp_key_engineType-->${temp["engineType"]}")
//
//
//            val result = PapagoResult()
//            result.srcLangType = temp["srcLangType"].toString()
//            result.tarLangType = temp["tarLangType"].toString()
//            result.translatedText = temp["translatedText"].toString()
//            result.engineType = temp["engineType"].toString()
//            result.classType = Constants.TRANSLATION
//            result.originText = viewModel.query.replace(getString(R.string.translation), "").trim()
//            allItemsAdapter.addData(result)
//
//
//        }
    }

    /**
     * 어댑터 세팅
     */
    private fun adapterSetting() {
        Log.e("cyc","adapterSetting")
//        allItemsAdapter = AllItemsAdapter(requireContext(),allChildRecyclerListener)
        allItemsAdapter = AllItemsAdapter(requireContext()) {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            intent.putExtra(Constants.DITAIL_WEB_LOAD_URL, it)
            startActivity(intent)
        }

        val allLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvOutAll.apply {
            layoutManager = allLayoutManager
            adapter = allItemsAdapter
        }
    }
}


//override fun onItemClick(category: Int, position: Int) {
//        // 클릭했을 때 행동이 무엇인가?
//        // -> WebActivity 띄우기
//        //    -> WebActivity를 띄우기 위해서 필요한 것?
//        //       -> link가 필요함
//        //
//        val intent = Intent(requireContext(),WebViewActivity::class.java)
////        intent.putExtra(Constants.DITAIL_WEB_LOAD_URL,tempResultSearchAll[])
//    }


//private var allChildRecyclerListener = object : AllChildRecyclerListener {
//        override fun onItemClick(link: String) {
//            val intent = Intent(requireContext(), WebViewActivity::class.java)
//            intent.putExtra(Constants.DITAIL_WEB_LOAD_URL, link)
//            startActivity(intent)
//        }
//    }


//Log.e("yyyy", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> $it")
//// it -> json형식의 스트링으로 내려옴
//val message = JSONObject(it).getJSONObject("message")
//Log.e("cyc", "message--->${message}")
//
//val result = message.getJSONObject("result")
//Log.e("cyc", "result--->${result}")
//
//Log.e("cyc", "")
//
//val srcLangType = result.get("srcLangType")
//Log.e("cyc", "srcLangType--->${srcLangType}")
//
//val tarLangType = result.get("tarLangType")
//Log.e("cyc", "tarLangType--->${tarLangType}")
//
//val translatedText = result.get("translatedText")
//Log.e("cyc", "translatedText--->${translatedText}")
//
//Log.e("cyc", "")
//
//val test = Gson().fromJson(it, Test::class.java)
//Log.e("cyc", "test--->${test}")
//
//val test2 = Gson().toJson(it)
//Log.e("cyc", "test2--->${test2}")
//
//val map = Gson().fromJson(it, Map::class.java)
//Log.e("cyc", "map-->${map}")
//Log.e("cyc", " map.keys-->${map.keys}")
//Log.e("cyc", " map[message]-->${map["message"]}")
//val message3 = map["message"] as Map<* ,*>
//Log.e("cyc", " message3-->${message3}")
//message3["result"]
//Log.e("cyc","message3[result]-->${message3["result"]}")
//val temp = message3["result"] as Map<*, *>
//Log.e("cyc","temp--->${temp}")
////            val srcLangType3 = temp["srcLangType"]
//Log.e("cyc","temp_key_srcLangType-->${temp["srcLangType"]}")
//
//val result4 = Gson().fromJson(Gson().toJson(temp), Result::class.java)
//Log.e("cyc", "result4-->${result4}")
//
////            Log.e("cyc", " result3-->${result3}")
////            result3["srcLangType"]
////            result3["tarLangType"]
////            result3["translatedText"]
////            Log.e("cyc", "result3[srcLangType]--->${result3["srcLangType"]}")
////            Log.e("cyc", "result3[tarLangType]--->${result3["tarLangType"]}")
////            Log.e("cyc", "result3[translatedText]--->${result3["translatedText"]}")
//
//
////            val result3 = Gson().fromJson(map["message"].toString(),Map::class.java)
//
////tojsonTress
////tojson
//
//
//val srcLangType2 = test.message.result.srcLangType
//Log.e("cyc", "srcLangType2--->${srcLangType2}")
//
//
//val tarLangType2 = test.message.result.tarLangType
//Log.e("cyc", "tarLangType2--->${tarLangType2}")
//
//
//val translatedText2 = test.message.result.translatedText
//Log.e("cyc", "translatedText2--->${translatedText2}")
//
