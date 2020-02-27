package com.kyawt.dotahero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kyawt.dotahero.api.DotaApi
import com.kyawt.dotahero.model.Teams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamViewModel :ViewModel(){
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<Boolean> = MutableLiveData()
    var result : MutableLiveData<List<Teams>> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> = loading
    fun getError() : LiveData<Boolean> = error
    fun getResult() : LiveData<List<Teams>> = result

    private var dotaApi : DotaApi = DotaApi()

    fun setData(){
        loading.value = true

        var call = dotaApi.getTeamResults()
        call.enqueue(object : Callback<List<Teams>> {
            override fun onFailure(call: Call<List<Teams>>, t: Throwable) {
                loading.value = false
                error.value = true

                Log.d("Error",t.toString())

            }

            override fun onResponse(call: Call<List<Teams>>, response: Response<List<Teams>>) {

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