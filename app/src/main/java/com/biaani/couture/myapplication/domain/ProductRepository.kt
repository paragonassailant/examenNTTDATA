package com.biaani.couture.myapplication.domain

import androidx.lifecycle.Observer
import com.biaani.couture.myapplication.data.datasource.web.webds.ProductWebDS
import com.biaani.couture.myapplication.data.entities.Products
import com.biaani.couture.myapplication.sys.util.Constants.Companion.RETROFIT_FAILURE
import com.biaani.couture.myapplication.sys.util.ErrorObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductRepository @Inject constructor(private val webDS: ProductWebDS) {

    fun request(observer: Observer<List<Products>>, error: Observer<ErrorObserver>){
        webDS.request(builrequest(observer,error),error)
    }

    private fun builrequest(observer: Observer<List<Products>>, error: Observer<ErrorObserver>): Observer<List<Products>> {
      return Observer {
          if (it.isEmpty()){
              error.onChanged(ErrorObserver(RETROFIT_FAILURE))
          }else{
              CoroutineScope(Dispatchers.IO).launch {
                  observer.onChanged(it)
              }
          }
      }
    }
}