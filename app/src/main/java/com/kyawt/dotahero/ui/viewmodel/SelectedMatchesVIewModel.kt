package com.kyawt.dotahero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kyawt.dotahero.model.Matches
import com.kyawt.dotahero.model.ProPlayer
import com.kyawt.dotahero.model.Teams

class SelectedMatchesViewModel :ViewModel(){
    private var detailMatches  : MutableLiveData<Matches> = MutableLiveData()
    private var detailPlayer : MutableLiveData<ProPlayer> = MutableLiveData()
    private var detailTeam : MutableLiveData<Teams> = MutableLiveData()

    fun getDetailMatches(): LiveData<Matches> =detailMatches
    fun getDetailPlayer():LiveData<ProPlayer> = detailPlayer
    fun getDetailTeam() : LiveData<Teams> = detailTeam

    fun setSelectedMatches(matches:Matches)
    {
        detailMatches.value = matches
        Log.d("Selected Matches", matches.toString())
    }

    fun setPlayer(players:ProPlayer){
        detailPlayer.value = players
    }

    fun setTeam(teams:Teams){
        detailTeam.value = teams
    }
}