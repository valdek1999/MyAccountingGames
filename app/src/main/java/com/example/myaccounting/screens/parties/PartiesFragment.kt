package com.example.myaccounting.screens.parties

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
import com.example.myaccounting.databinding.FragmentPartiesBinding
import com.example.myaccounting.screens.games.GamesAdapter
import com.example.myaccounting.screens.games.GamesFragmentDirections
import com.example.myaccounting.screens.games.GamesViewModel
import com.example.myaccounting.screens.games.GamesViewModelFactory

class PartiesFragment : Fragment() {
    lateinit var viewModel: PartiesViewModel
    lateinit var mainDatabase: MainDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentPartiesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_parties, container, false
        )
        val application = requireNotNull(this.activity).application
        val dao = MainDatabase.getInstance(application).getMainDatabaseDao()
        val viewModelFactory = PartiesViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PartiesViewModel::class.java)
        val adapter = PartiesAdapter()
        binding.recyclerViewParties.adapter = adapter
        viewModel.parties_list.observe(viewLifecycleOwner, Observer { newGames ->
            if(newGames !=null)
                adapter.data = newGames
        })

        binding.buttonNewParties.setOnClickListener {
            this.findNavController().navigate(
                PartiesFragmentDirections.actionPartiesFragmentToGamesFragment()
            )
            viewModel.doneNavigating()
        }

        return binding.root
    }
}