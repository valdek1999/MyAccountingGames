package com.example.myaccounting.screens.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myaccounting.R
import com.example.myaccounting.database.MainDatabase
import com.example.myaccounting.databinding.FragmentGamesBinding

class GamesFragment : Fragment() {

    lateinit var viewModel: GamesViewModel
    lateinit var mainDatabase: MainDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentGamesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_games, container, false
        )
        val application = requireNotNull(this.activity).application
        val dao = MainDatabase.getInstance(application).getMainDatabaseDao()
        val viewModelFactory = GamesViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(GamesViewModel::class.java)
        val adapter = GamesAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.games_list.observe(viewLifecycleOwner, Observer { newGames ->
            if(newGames !=null)
                adapter.data = newGames
        })

        binding.buttonNewGame.setOnClickListener {
            this.findNavController().navigate(
                GamesFragmentDirections.actionGamesFragmentToGameFragment()

            )
            viewModel.doneNavigating()
        }

        return binding.root
    }


}