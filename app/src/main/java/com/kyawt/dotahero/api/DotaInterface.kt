package com.kyawt.dotahero.api

import com.kyawt.dotahero.model.DotaHero
import com.kyawt.dotahero.model.Matches
import com.kyawt.dotahero.model.ProPlayer
import com.kyawt.dotahero.model.Teams
import retrofit2.Call
import retrofit2.http.GET

interface DotaInterface {
    @GET("heroStats ")
    fun getHero(): Call<List<DotaHero>>

    @GET("proMatches")
    fun getProMatch():Call<List<Matches>>

    @GET("teams")
    fun getProTeam() : Call<List<Teams>>

    @GET("proPlayers")
    fun getProPlayer(): Call<List<ProPlayer>>
}