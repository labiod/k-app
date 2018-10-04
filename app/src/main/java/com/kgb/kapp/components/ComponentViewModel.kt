package com.kgb.kapp.components

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ComponentViewModel : ViewModel() {
    private val _component = MutableLiveData<ComponentDAO>()
    val component : LiveData<ComponentDAO>
        get() = _component

    fun post(componentDAO: ComponentDAO) {
        _component.postValue(componentDAO)
    }
}