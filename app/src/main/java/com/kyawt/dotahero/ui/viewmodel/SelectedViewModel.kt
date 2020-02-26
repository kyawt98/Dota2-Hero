package com.kyawt.dotahero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kyawt.dotahero.model.DotaHero

class SelectedViewModel :ViewModel(){
    private var detailHero  : MutableLiveData<List<DotaHero>> = MutableLiveData()

    fun getDetailHero(): LiveData<List<DotaHero>> =detailHero

    fun setSelectedHero(hero: List<DotaHero>)
    {
        detailHero.value = hero
        Log.d("Selected Hero", hero.toString())
    }
}
