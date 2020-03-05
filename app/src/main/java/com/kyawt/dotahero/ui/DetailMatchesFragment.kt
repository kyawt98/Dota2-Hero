package com.kyawt.dotahero.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.kyawt.dotahero.R
import com.kyawt.dotahero.adapter.MatchDetailAdapter
import com.kyawt.dotahero.model.Matches
import com.kyawt.dotahero.model.ProPlayer
import com.kyawt.dotahero.model.Teams
import com.kyawt.dotahero.ui.viewmodel.SelectedMatchesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_matches.*

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchesFragment : Fragment() {
    lateinit var matchDetailAdapter: MatchDetailAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private  var selectedMatchesViewModel : SelectedMatchesViewModel = SelectedMatchesViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail_matches, container, false)

        var root = inflater.inflate(R.layout.fragment_detail_matches, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(activity)
        matchDetailAdapter = MatchDetailAdapter()
        recycler_winner.adapter = matchDetailAdapter
        recycler_winner.layoutManager = viewManager

        observeViewModel()
    }

    fun observeViewModel(){
         selectedMatchesViewModel = ViewModelProviders.of(activity!!)
            .get(SelectedMatchesViewModel::class.java)

        selectedMatchesViewModel.getDetailMatches().observe(viewLifecycleOwner, Observer<Matches> {

            txt_win_team_name.text = it.radiant_name
            txt_win_value.text = it.radiant_score
            txt_captains_mode_value.text = it.duration
            txt_lose_value.text = it.dire_score
            txt_win_overview_label_value.text = it.radiant_name
            txt_lose_overview_label_value.text = it.dire_name
        })

        selectedMatchesViewModel.getDetailTeam().observe(viewLifecycleOwner, Observer<Teams> {

            var win_team_id = this.id
            var team_id = it.team_id

            if(win_team_id.equals(team_id)) {
                Picasso.get()
                    .load(it.logo_url)
                    .placeholder(R.drawable.ic_dota2)
                    .into(img_team)
                Log.d("win", this.id.toString())
            }
        })

    }

}

