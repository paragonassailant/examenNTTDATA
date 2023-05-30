package com.biaani.couture.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.biaani.couture.myapplication.data.entities.Products
import com.biaani.couture.myapplication.domain.ProductRepository
import com.biaani.couture.myapplication.sys.util.ErrorObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {
    val onSuccess = MutableLiveData<List<Products>>()
    val onError by lazy { MutableLiveData<ErrorObserver>() }

    fun request() {
        repository.request(getData(), getError())
    }

    private fun getData(): Observer<List<Products>> {
        return Observer {
            onSuccess.postValue(it)
        }
    }

    private fun getError(): Observer<ErrorObserver> {
        return Observer {
            onError.postValue(it)
        }
    }
}