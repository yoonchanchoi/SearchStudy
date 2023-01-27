package com.example.searchstudy.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchstudy.network.models.dto.search.SearchData
import com.example.searchstudy.util.Pref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val pref: Pref
) : ViewModel() {


}