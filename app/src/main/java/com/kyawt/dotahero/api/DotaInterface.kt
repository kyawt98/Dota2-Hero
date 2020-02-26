package com.kyawt.dotahero.api

import com.kyawt.dotahero.model.DotaHero
import retrofit2.Call
import retrofit2.http.GET

interface DotaInterface {
    @GET("heroStats ")
    fun getHero(): Call<List<DotaHero>>
}