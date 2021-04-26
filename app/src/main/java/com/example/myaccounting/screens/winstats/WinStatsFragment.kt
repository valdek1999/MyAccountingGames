package com.example.myaccounting.screens.winstats

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
import com.example.myaccounting.databinding.FragmentWinStatsBinding
import com.example.myaccounting.screens.parties.PartiesAdapter
import com.example.myaccounting.screens.parties.PartiesFragmentDirections
import com.example.myaccounting.screens.parties.PartiesViewModel
import com.example.myaccounting.screens.parties.PartiesViewModelFactory


class WinStatsFragment : Fragment() {
    lateinit var viewModel: WinStatsViewModel
    lateinit var mainDatabase: MainDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentWinStatsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_win_stats, container, false
        )
        val application = requireNotNull(this.activity).application
        val dao = MainDatabase.getInstance(application).getMainDatabaseDao()
        val viewModelFactory = WinStatsViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(WinStatsViewModel::class.java)

        val adapter = WinStatsAdapter()
        binding.recyclerViewWinstats.adapter = adapter

        viewModel.games_list.observe(viewLifecycleOwner, Observer { newGames ->
            if(newGames !=null)
            {
                adapter.data_parties = viewModel.parties_list.value!!
                adapter.data_games = viewModel.games_list.value!!
                adapter.data_name = binding.editTextTextPersonName.text.toString()
            }
        })
        binding.button.setOnClickListener {
            if(binding.editTextTextPersonName.text.toString() !="")
            {
                viewModel.SearchListOfGames(binding.editTextTextPersonName.text.toString())
            }
        }

        return binding.root
    }
}