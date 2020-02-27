package com.kyawt.dotahero.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.kyawt.dotahero.R
import com.kyawt.dotahero.adapter.TeamAdapter
import com.kyawt.dotahero.model.Teams
import com.kyawt.dotahero.ui.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.fragment_team.*
import kotlinx.android.synthetic.main.fragment_team.view.*

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment() {

    private var teamAdapter: TeamAdapter = TeamAdapter()
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var teamViewModel: TeamViewModel = TeamViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var teamViewModel =
            ViewModelProviders.of(this).get(TeamViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_team, container, false)

        var tabMatches = root.tab_matches
        tabMatches.setOnClickListener { view: View ->
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.screen_container, MatchFragment())
                .addToBackStack(null) //when pressing back key
                .commit()
        }

        var tabHero = root.tab_heroes
        tabHero.setOnClickListener { view: View ->
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.screen_container, HeroFragment())
                .addToBackStack(null) //when pressing back key
                .commit()
        }

        var tabTeam = root.tab_teams
        tabTeam.setOnClickListener { view: View ->
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.screen_container, TeamFragment())
                .addToBackStack(null) //when pressing back key
                .commit()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recycler_team.adapter = teamAdapter
        recycler_team.layoutManager = viewManager

//        matchAdapter.setOnClickListener(this)
        observeViewModel()
    }

    fun observeViewModel() {

        teamViewModel = ViewModelProviders.of(this)
            .get(TeamViewModel::class.java)

        teamViewModel.getError().observe(viewLifecycleOwner, Observer<Boolean> { isError ->
            if (isError) {
                LoadingBar.visibility = View.GONE
                txtErrorMessage.visibility = View.VISIBLE
            }
        })

        teamViewModel.getResult().observe(viewLifecycleOwner, Observer<List<Teams>> { result ->
            LoadingBar.visibility = View.GONE
            recycler_team.visibility = View.VISIBLE
            teamAdapter.updateData(result)
        })

        teamViewModel.getLoading().observe(viewLifecycleOwner, Observer<Boolean> { isLoading ->
            LoadingBar.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
            if (isLoading) {
                LoadingBar.visibility = View.VISIBLE
                recycler_team.visibility = View.GONE
            }
        })

        teamViewModel.setData()
    }

    override fun onResume() {
        super.onResume()
        teamViewModel.setData()
    }

//    override fun Onclick(hero: DotaHero) {
//        val selectedViewModel: SelectedViewModel =
//            ViewModelProviders.of(activity!!).get(SelectedViewModel::class.java)
//        selectedViewModel.setSelectedHero(hero)
//        Log.d("Hero", hero.toString())
//        activity!!.supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.screen_container, DetailFragment())
//            .addToBackStack(null) //when pressing back key
//            .commit()
//
////        Toast.makeText(context,selectedViewModel.getDetailHero().value.toString(),Toast.LENGTH_LONG).show()
//    }

}
