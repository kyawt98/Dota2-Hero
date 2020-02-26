package com.kyawt.dotahero.api
import com.kyawt.dotahero.model.DotaHero
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DotaApi {
    lateinit var dotaInterface: DotaInterface

    companion object {
        const val base_url = " https://api.opendota.com/api/"
    }

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dotaInterface = retrofit.create(DotaInterface::class.java)
    }

    fun getResults(): Call<List<DotaHero>> {
        return dotaInterface.getHero()
    }

}