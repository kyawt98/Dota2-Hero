package com.kyawt.dotahero.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.kyawt.dotahero.R
import com.kyawt.dotahero.model.DotaHero
import com.kyawt.dotahero.model.Matches
import com.kyawt.dotahero.model.ProPlayer
import com.kyawt.dotahero.model.Teams
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hero.view.*
import kotlinx.android.synthetic.main.item_matches.view.*

class MatchAdapter (var matchList:List<Matches> = ArrayList(), var playerList:List<ProPlayer> =ArrayList(), var teamList:List<Teams> =ArrayList()): RecyclerView.Adapter<MatchAdapter.MatchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_matches,parent,false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return matchList.size

        Log.d("Result", matchList.size.toString())
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matchList.get(position))
    }

    inner class MatchViewHolder(itemView : View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private lateinit var matches:Matches
//        private var view:View = itemView

        fun bind(matches: Matches){
            this.matches = matches
            itemView.txtMatchIDItem?.text = matches?.match_id
            itemView.txtDurationItem?.text = matches?.duration
            itemView.txtRadiantItem?.text = matches?.radiant_name
            itemView.txtDireItem?.text = matches?.dire_name
            itemView.txtLeagueItem?.text = matches?.league_name
        }

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            mCLickListener?.Onclick(matches)
        }
    }

    fun updateData(matchList: List<Matches>){
        this.matchList = matchList
        notifyDataSetChanged()
    }

    fun updatePlayerData(playerList: List<ProPlayer>){
        this.playerList = playerList
        notifyDataSetChanged()
    }

    fun updateTeamData(teamList: List<Teams>){
        this.teamList = teamList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun Onclick(matches: Matches)
    }

    var mCLickListener : ClickListener ?= null

    fun setOnClickListener(clickListener: ClickListener){
        this.mCLickListener = clickListener
    }
}