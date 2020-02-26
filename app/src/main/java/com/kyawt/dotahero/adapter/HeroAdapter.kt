package com.kyawt.dotahero.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.kyawt.dotahero.R
import com.kyawt.dotahero.model.DotaHero
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroAdapter (var heroList:List<DotaHero> = ArrayList()): RecyclerView.Adapter<HeroAdapter.HeroViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero,parent,false)
        return HeroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return heroList.size

        Log.d("Result", heroList.size.toString())
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(heroList.get(position))
    }

    inner class HeroViewHolder(itemView : View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private lateinit var dotaHero:DotaHero
//        private var view:View = itemView

        fun bind(hero:DotaHero){
            this.dotaHero = hero

            Picasso.get()
                .load(dotaHero.img)
                .placeholder(R.drawable.ic_dota2)
                .resize(100,60)
                .into(itemView.imgHero)

            Log.d("Icon", dotaHero.img.toString())
            itemView.txtProBItem.text = dotaHero.null_pick.toString()
            itemView.txtProBanItem.text = dotaHero.pro_ban.toString()
            itemView.txtProPickItem.text = dotaHero.pro_pick.toString()
            itemView.txtProWinItem.text = dotaHero.pro_win.toString()
        }

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            mCLickListener?.Onclick(dotaHero)
        }
    }

    fun updateData(heroList: List<DotaHero>){
        this.heroList = heroList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun Onclick(hero: DotaHero)
    }

    var mCLickListener : ClickListener ?= null

    fun setOnClickListener(clickListener: ClickListener){
        this.mCLickListener = clickListener
    }
}