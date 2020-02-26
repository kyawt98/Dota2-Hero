package com.kyawt.dotahero.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.kyawt.dotahero.R
import com.kyawt.dotahero.model.DotaHero
import com.kyawt.dotahero.ui.viewmodel.SelectedViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false)
        var root =inflater.inflate(R.layout.fragment_detail, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedViewModel = ViewModelProviders.of(activity!!)
            .get(SelectedViewModel::class.java)

        selectedViewModel.getDetailHero().observe(viewLifecycleOwner, Observer<List<DotaHero>> {

            Picasso.get()
                .load(it.get(0).icon)
                .placeholder(R.drawable.ic_dota2)
                .into(img_hero)
            txt_base_attack_value.text = it.get(0).base_attack_min.toString()
            txt_attack_range_value.text = it.get(0).attack_range.toString()
            txt_attack_speed_value.text = it.get(0).attack_rate.toString()
            txt_projectile_value.text = it.get(0).projectile_speed.toString()
            txt_health_value.text = it.get(0).base_health.toString()
            txt_health_regen_value.text = it.get(0).base_health_regen.toString()
            txt_mana_value.text = it.get(0).base_mana.toString()
            txt_mana_regen_value.text = it.get(0).base_mana_regen.toString()
            txt_base_armor_value.text = it.get(0).base_armor.toString()
            txt_magic_resistance_value.text = it.get(0).base_mr.toString()
            txt_move_speed_value.text = it.get(0).move_speed.toString()
            txt_turn_speed_value.text = it.get(0).turn_rate.toString()
            txt_cm_enable_value.text = it.get(0).cm_enabled.toString()
            txt_hero_attack_type.text = it.get(0).attack_type
            txt_hero_role.text = it.get(0).roles.toString()

            var index: Int = 0
            it.forEach {
                txt_hero_name.text = it.localized_name
                index++
            }
//            Picasso.get()
//                .load
//                .placeholder(R.drawable.student)
//                .into(imgStudentDetailItem)
//            txtDetailStudentNameValueItem.text=it.student.user.name
//            txtDetailStudentGenderValueItem.text= it.student.gender
//            txtDetailStudentNrcValueItem.text = it.student.nrc_no
//            txtDetailStudentBirthdayValueItem.text = it.student.date_of_birth
//            txtDetailStudentAddressValueItem.text = it.student.address
//            txtDetailStudentPhoneValueItem.text = it.student.phone_no
//            txtDetailStudentClassValueItem.text = it.`class`.class_name
        })
    }


}
