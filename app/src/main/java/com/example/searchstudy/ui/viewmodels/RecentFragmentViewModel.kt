package com.example.searchstudy.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecentFragmentViewModel @Inject constructor(
) : ViewModel() {

    private val _loginState = MutableLiveData<List<String>>()
    val loginState: LiveData<List<String>>
        get() = _loginState
}