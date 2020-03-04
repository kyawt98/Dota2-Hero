package com.kyawt.dotahero.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.kyawt.dotahero.R
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

        var selectedMatchesViewModel = ViewModelProviders.of(activity!!)
            .get(SelectedMatchesViewModel::class.java)

        selectedMatchesViewModel.getDetailMatches().observe(viewLifecycleOwner, Observer<Matches> {

            var win_team_id = it.radiant_team_id

            txt_win_team_name.text = it.radiant_name
            txt_win_value.text = it.radiant_score
            txt_captains_mode_value.text = it.duration
            txt_lose_value.text = it.dire_score
            txt_win_overview_label_value.text = it.radiant_name
            txt_lose_overview_label_value.text = it.dire_name

            selectedMatchesViewModel.getDetailTeam().observe(viewLifecycleOwner, Observer<Teams> {
                var team_id = it.team_id

                if (win_team_id == team_id || win_team_id != null) {

                    Picasso.get()
                        .load(it.logo_url)
                        .placeholder(R.drawable.ic_dota2)
                        .into(img_team)
                    Log.d("Team", it.logo_url.toString())

                }
            })

        })




        selectedMatchesViewModel.getDetailPlayer().observe(viewLifecycleOwner, Observer<ProPlayer> {
            var team_id = it.team_id


//                    txt_hero_name.text = it.localized_name
//                    txt_base_attack_value.text = it.base_attack_min.toString()
//                    txt_attack_range_value.text = it.attack_range.toString()
//                    txt_attack_speed_value.text = it.attack_rate.toString()
//                    txt_projectile_value.text = it.projectile_speed.toString()
//                    txt_health_value.text = it.base_health.toString()
//                    txt_health_regen_value.text = it.base_health_regen.toString()
//                    txt_mana_value.text = it.base_mana.toString()
//                    txt_mana_regen_value.text = it.base_mana_regen.toString()
//                    txt_base_armor_value.text = it.base_armor.toString()
//                    txt_magic_resistance_value.text = it.base_mr.toString()
//                    txt_move_speed_value.text = it.move_speed.toString()
//                    txt_turn_speed_value.text = it.turn_rate.toString()
//                    txt_cm_enable_value.text = it.cm_enabled.toString()
//                    txt_hero_attack_type.text = it.attack_type
//                    txt_hero_role.text = it.roles.toString()
//                    txt_strength_value.text = it.base_str.toString()
//                    txt_agility_value.text = it.base_agi.toString()
//                    txt_no_of_leg_value.text = it.legs.toString()
//                    txt_intelligent_value.text = it.base_int.toString()


        })
    }

}
