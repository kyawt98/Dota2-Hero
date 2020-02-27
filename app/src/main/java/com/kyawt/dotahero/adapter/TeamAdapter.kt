package com.kyawt.dotahero.adapter

import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.kyawt.dotahero.R

import com.kyawt.dotahero.model.Teams
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hero.view.*
import kotlinx.android.synthetic.main.item_matches.view.*
import kotlinx.android.synthetic.main.item_matches.view.txtLeagueItem
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter (var teamList:List<Teams> = ArrayList()): RecyclerView.Adapter<TeamAdapter.TeamViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_team,parent,false)
        return TeamViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 100

        Log.d("Result", teamList.size.toString())
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teamList.get(position))
    }

    inner class TeamViewHolder(itemView : View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private lateinit var teams:Teams
//        private var view:View = itemView

        fun bind(teams: Teams) {
            this.teams = teams
            if (teams.logo_url == null || teams.logo_url.isEmpty()) {
                Picasso.get()
                    .load(R.drawable.ic_dota2)
                    .placeholder(R.drawable.ic_dota2)
                    .resize(50, 50)
                    .into(itemView?.imgTeam)
            } else {
                Picasso.get()
                    .load(teams?.logo_url)
                    .placeholder(R.drawable.ic_dota2)
                    .into(itemView?.imgTeam)
            }

            if (teams.name == null || teams.name.isEmpty() || teams.wins > 100) {

                itemView.txtNameItem?.text = "Unknown"
                itemView.txtWinItem?.text = teams?.wins.toString()
                itemView.txtLossesItem?.text = teams?.losses
                itemView.txtLastPlayItem?.text = teams?.last_match_time
            }else{
                itemView.txtNameItem?.text = teams?.name
                itemView.txtWinItem?.text = teams?.wins.toString()
                itemView.txtLossesItem?.text = teams?.losses
                itemView.txtLastPlayItem?.text = teams?.last_match_time
            }
        }

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            mCLickListener?.Onclick(teams)
        }
    }

    fun updateData(matchList: List<Teams>){
        this.teamList = matchList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun Onclick(teams: Teams)
    }

    var mCLickListener : ClickListener ?= null

    fun setOnClickListener(clickListener: ClickListener){
        this.mCLickListener = clickListener
    }
}