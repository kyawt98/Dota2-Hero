package com.kyawt.dotahero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kyawt.dotahero.api.DotaApi
import com.kyawt.dotahero.model.DotaHero
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroViewModel :ViewModel(){
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<Boolean> = MutableLiveData()
    var result : MutableLiveData<List<DotaHero>> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> = loading
    fun getError() : LiveData<Boolean> = error
    fun getResult() : LiveData<List<DotaHero>> = result

    private var dotaApi : DotaApi = DotaApi()

    fun setData(){
        loading.value = true

        var call = dotaApi.getResults()
        call.enqueue(object : Callback<List<DotaHero>>{
            override fun onFailure(call: Call<List<DotaHero>>, t: Throwable) {
                loading.value = false
                error.value = true

                Log.d("Error",t.toString())

            }

            override fun onResponse(call: Call<List<DotaHero>>, response: Response<List<DotaHero>>) {

                response.isSuccessful.let {
                    loading.value = false

                    var resultList = response?.body()
                    result.value = resultList
                    Log.d("View Result", resultList.toString())


                }
            }
        })
    }
}