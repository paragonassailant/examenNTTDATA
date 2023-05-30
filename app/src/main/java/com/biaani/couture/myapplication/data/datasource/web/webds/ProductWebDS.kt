package com.biaani.couture.myapplication.data.datasource.web.webds

import androidx.lifecycle.Observer
import com.biaani.couture.myapplication.data.datasource.web.api.WebServices
import com.biaani.couture.myapplication.data.datasource.web.response.OnProductResponse
import com.biaani.couture.myapplication.data.entities.Products
import com.biaani.couture.myapplication.sys.util.Constants
import com.biaani.couture.myapplication.sys.util.Constants.Companion.RETROFIT_FAILURE
import com.biaani.couture.myapplication.sys.util.ErrorObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductWebDS @Inject constructor(private val webServices: WebServices) {

    fun request(observer: Observer<List<Products>>, error:Observer<ErrorObserver>){
        webServices.getProducts().enqueue(object :Callback<OnProductResponse>{
            override fun onResponse(call: Call<OnProductResponse>, response: Response<OnProductResponse>) {
                CoroutineScope(Dispatchers.IO).launch {
                    when(response.code()){
                        200->{
                            if (response.body()?.result != null) {
                                observer.onChanged(response.body()!!.result)
                            } else {
                                error.onChanged(ErrorObserver(RETROFIT_FAILURE, response.message()))
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<OnProductResponse>, t: Throwable) {
                error.onChanged(ErrorObserver(Constants.RETROFIT_FAILURE, t.message))
            }

        })
    }
}