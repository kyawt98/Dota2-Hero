package com.kyawt.dotahero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kyawt.dotahero.model.Matches

class SelectedMatchesViewModel :ViewModel(){
    private var detailMatches  : MutableLiveData<Matches> = MutableLiveData()

    fun getDetailMatches(): LiveData<Matches> =detailMatches

    fun setSelectedMatches(matches:Matches)
    {
        detailMatches.value = matches
        Log.d("Selected Matches", matches.toString())
    }
}