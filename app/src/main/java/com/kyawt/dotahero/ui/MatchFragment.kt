package com.kyawt.dotahero.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.kyawt.dotahero.R
import com.kyawt.dotahero.adapter.MatchAdapter
import com.kyawt.dotahero.model.Matches
import com.kyawt.dotahero.model.ProPlayer
import com.kyawt.dotahero.model.Teams
import com.kyawt.dotahero.ui.viewmodel.MatchViewModel
import com.kyawt.dotahero.ui.viewmodel.SelectedMatchesViewModel
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_match.view.*

/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment(), MatchAdapter.ClickListener {

    lateinit var matchAdapter: MatchAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var matchViewModel: MatchViewModel = MatchViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var matchViewModel =
            ViewModelProviders.of(this).get(MatchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_match, container, false)

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
        matchAdapter = MatchAdapter()
        viewManager = LinearLayoutManager(activity)
        recycler_matches.adapter = matchAdapter
        recycler_matches.layoutManager = viewManager

        matchAdapter.setOnClickListener(this)
        observeViewModel()
    }

    fun observeViewModel() {

        matchViewModel = ViewModelProviders.of(this)
            .get(MatchViewModel::class.java)

        matchViewModel.getError().observe(viewLifecycleOwner, Observer<Boolean> { isError ->
            if (isError) {
                LoadingBar.visibility = View.GONE
                txtErrorMessage.visibility = View.VISIBLE
            }
        })

        matchViewModel.getResult().observe(viewLifecycleOwner, Observer<List<Matches>> { result ->
            LoadingBar.visibility = View.GONE
            recycler_matches.visibility = View.VISIBLE
            matchAdapter.updateData(result)
        })

        matchViewModel.getLoading().observe(viewLifecycleOwner, Observer<Boolean> { isLoading ->
            LoadingBar.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
            if (isLoading) {
                LoadingBar.visibility = View.VISIBLE
                recycler_matches.visibility = View.GONE
            }
        })

        matchViewModel.setData()
    }

    override fun onResume() {
        super.onResume()
        matchViewModel.setData()
    }

    override fun Onclick(matches: Matches) {
        val selectMatchesViewModel: SelectedMatchesViewModel =
            ViewModelProviders.of(activity!!).get(SelectedMatchesViewModel::class.java)
        selectMatchesViewModel.setSelectedMatches(matches)
//        Log.d("Matches", matches.toString())
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.screen_container, DetailMatchesFragment())
            .addToBackStack(null) //when pressing back key
            .commit()
//        Toast.makeText(context,selectMatchesViewModel.getDetailMatches().value.toString(),Toast.LENGTH_LONG).show()
 }

}
