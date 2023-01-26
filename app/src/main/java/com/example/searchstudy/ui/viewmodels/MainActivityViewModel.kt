package com.example.searchstudy.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(

) : ViewModel() {


    private val _searchData = MutableLiveData<ArrayList<String>>()
    val searchData: LiveData<ArrayList<String>>
        get() = _searchData

    fun addSearchData(searchText: String){
        _searchData.value?.add(searchText)
    }

}