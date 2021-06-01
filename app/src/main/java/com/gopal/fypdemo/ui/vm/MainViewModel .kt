package com.gopal.fypdemo.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _res = MutableLiveData<String>()
    val res: LiveData<String>
        get() = _res

}