package com.example.searchstudy.ui.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchstudy.network.managers.SearchManager
import com.example.searchstudy.network.models.response.*
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


enum class MainUi {
    NOTEMPTY, EMPTY
}

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val searchManager: SearchManager
) : ViewModel() {





//    var blogItemsArraylist = ArrayList<BlogItems>()
//    var cafeItemsArraylist = ArrayList<CafeItems>()
//    var dictionaryItemsArraylist = ArrayList<DictionaryItems>()
//    var imgItemsArraylist = ArrayList<ImgItems>()
    private val _searchText = MutableLiveData<String>()
    val searchText : LiveData<String>
        get() = _searchText

    private val _blogItemsArraylist = MutableLiveData<ArrayList<ViewItems>>()
    val blogItemsArraylist : LiveData<ArrayList<ViewItems>>
        get() = _blogItemsArraylist

    private val _cafeItemsArraylist = MutableLiveData<ArrayList<ViewItems>>()
    val cafeItemsArraylist : LiveData<ArrayList<ViewItems>>
        get() = _cafeItemsArraylist

    private val _dictionaryItemsArraylist = MutableLiveData<ArrayList<DictionaryItems>>()
    val dictionaryItemsArraylist : LiveData<ArrayList<DictionaryItems>>
        get() = _dictionaryItemsArraylist

    private val _imgItemsArraylist = MutableLiveData<ArrayList<ImgItems>>()
    val imgItemsArraylist : LiveData<ArrayList<ImgItems>>
        get() = _imgItemsArraylist

    var viewItems= ArrayList<ViewItems>()
    var dictionaryItems= ArrayList<DictionaryItems>()
    var allItems = ArrayList<AllItems>()

//    private val _viewItemsArraylist = MutableLiveData<ArrayList<ViewItems>>()
//    val viewItemsArraylist : LiveData<ArrayList<ViewItems>>
//        get() = _viewItemsArraylist


    /**
     * search
     */
    fun searchQury(query: String){
        searchBlog(query)
        searCafe(query)
        searchDictionary(query)
        searchImg(query)
    }



    private fun searchBlog(query: String){
        val result = searchManager.searchBlog(query = query)
        result.enqueue(object : Callback<ResultSearchView>{
            override fun onResponse(call: Call<ResultSearchView>, response: Response<ResultSearchView>) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        Log.e("cyc","it.blogItem-->${it.viewitems}")
                        _blogItemsArraylist.postValue(it.viewitems)
//                        addAllData(it.viewitems)
//                        viewItemsArrayList.addSource(_blogItemsArraylist){
//                            viewItemsArrayList.value = _blogItemsArraylist.value
//
//                        }

                    }
                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }
            override fun onFailure(call: Call<ResultSearchView>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }

        })
    }

    private fun searCafe(query: String){
        val result = searchManager.searchCafe(query = query)
        result.enqueue(object : Callback<ResultSearchView>{
            override fun onResponse(
                call: Call<ResultSearchView>,
                response: Response<ResultSearchView>
            ) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        Log.e("cyc","it.cafeItem-->${it.viewitems}")
                        _cafeItemsArraylist.postValue(it.viewitems)
//                        addAllData(it.viewitems)

//                        viewItemsArrayList.addSource(_cafeItemsArraylist){
//                            viewItemsArrayList.value = _cafeItemsArraylist.value
//
//                        }
                    }
                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchView>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }

    private fun searchDictionary(query: String){
        val result = searchManager.searchDictionary(query = query)
        result.enqueue(object : Callback<ResultSearchDictionary>{
            override fun onResponse(
                call: Call<ResultSearchDictionary>,
                response: Response<ResultSearchDictionary>
            ) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        Log.e("cyc","Mainviewmodel___________________it.dictionaryItem-->${it.dictionaryItem}")
                        _dictionaryItemsArraylist.postValue(it.dictionaryItem)
//                        addAllData(it.dictionaryItem)
                        Log.e("cyc","dictionaryItemsArraylist--->${dictionaryItemsArraylist}")

                    }
                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchDictionary>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }

    private fun searchImg(query: String){
        val result = searchManager.searchImg(query = query)
        result.enqueue(object : Callback<ResultSearchImg>{
            override fun onResponse(
                call: Call<ResultSearchImg>,
                response: Response<ResultSearchImg>
            ) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        Log.e("cyc","it.imgItem-->${it.imgItem}")
                        _imgItemsArraylist.postValue(it.imgItem)

                    }
                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchImg>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }


    fun addAllData(allItems: ArrayList<AllItems>){
        allItems.addAll(allItems)
    }

    fun Allclear(){
        allItems.clear()
    }

}