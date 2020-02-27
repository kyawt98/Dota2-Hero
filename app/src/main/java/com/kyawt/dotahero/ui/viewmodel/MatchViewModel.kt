package com.kyawt.dotahero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kyawt.dotahero.api.DotaApi
import com.kyawt.dotahero.model.Matches
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchViewModel :ViewModel(){
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<Boolean> = MutableLiveData()
    var result : MutableLiveData<List<Matches>> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> = loading
    fun getError() : LiveData<Boolean> = error
    fun getResult() : LiveData<List<Matches>> = result

    private var dotaApi : DotaApi = DotaApi()

    fun setData(){
        loading.value = true

        var call = dotaApi.getMatchesResults()
        call.enqueue(object : Callback<List<Matches>> {
            override fun onFailure(call: Call<List<Matches>>, t: Throwable) {
                loading.value = false
                error.value = true

                Log.d("Error",t.toString())

            }

            override fun onResponse(call: Call<List<Matches>>, response: Response<List<Matches>>) {

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