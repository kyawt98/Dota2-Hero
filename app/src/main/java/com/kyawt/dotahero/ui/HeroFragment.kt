package com.kyawt.dotahero.ui


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.kyawt.dotahero.R
import com.kyawt.dotahero.adapter.HeroAdapter
import com.kyawt.dotahero.model.DotaHero
import com.kyawt.dotahero.ui.viewmodel.HeroViewModel
import com.kyawt.dotahero.ui.viewmodel.SelectedViewModel
import kotlinx.android.synthetic.main.fragment_hero.*
import kotlinx.android.synthetic.main.item_hero.*

/**
 * A simple [Fragment] subclass.
 */
class HeroFragment : Fragment(), HeroAdapter.ClickListener {

    private var heroAdapter: HeroAdapter = HeroAdapter()
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var heroViewModel: HeroViewModel = HeroViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_hero, container, false)
        heroViewModel =
            ViewModelProviders.of(this).get(HeroViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_hero, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recycler_hero.adapter = heroAdapter
        recycler_hero.layoutManager = viewManager

        heroAdapter.setOnClickListener(this)
        observeViewModel()
    }

    fun observeViewModel() {

        heroViewModel = ViewModelProviders.of(this)
            .get(HeroViewModel::class.java)

        heroViewModel.getError().observe(viewLifecycleOwner, Observer<Boolean> { isError ->
            if (isError) {
                LoadingBar.visibility = View.GONE
                txtErrorMessage.visibility = View.VISIBLE
            }
        })

        heroViewModel.getResult().observe(viewLifecycleOwner, Observer<List<DotaHero>> { result ->
            LoadingBar.visibility = View.GONE
            recycler_hero.visibility = View.VISIBLE
            heroAdapter.updateData(result)

            Log.d("Image", result.toString())
        })

        heroViewModel.getLoading().observe(viewLifecycleOwner, Observer<Boolean> { isLoading ->
            LoadingBar.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
            if (isLoading) {
                LoadingBar.visibility = View.VISIBLE
                recycler_hero.visibility = View.GONE
            }
        })

        heroViewModel.setData()
    }

    override fun onResume() {
        super.onResume()
        heroViewModel.setData()
    }

    override fun Onclick(hero: List<DotaHero>) {


            val selectedViewModel: SelectedViewModel =
                ViewModelProviders.of(activity!!).get(SelectedViewModel::class.java)
            selectedViewModel.setSelectedHero(hero)
            Log.d("Hero", hero.toString())
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.screen_container, DetailFragment())
                .addToBackStack(null) //when pressing back key
                .commit()

//        Toast.makeText(context,selectedViewModel.getDetailHero().value.toString(),Toast.LENGTH_LONG).show()
        }

}



