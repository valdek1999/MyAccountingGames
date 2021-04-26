package com.example.myaccounting.screens.title

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myaccounting.R
import com.example.myaccounting.screens.title.TitleFragmentDirections
import com.example.myaccounting.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        setHasOptionsMenu(true)//включаем вкладку с меню

        val binding: FragmentTitleBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_title, container, false)

        binding.buttonGames.setOnClickListener{
            Navigation.findNavController(it).navigate(TitleFragmentDirections.actionTitleFragmentToGamesFragment())
        }

        binding.buttonParties.setOnClickListener{
            Navigation.findNavController(it).navigate(TitleFragmentDirections.actionTitleFragmentToPartiesFragment())
        }

        binding.buttonWinStats.setOnClickListener{
            Navigation.findNavController(it).navigate(TitleFragmentDirections.actionTitleFragmentToWinStatsFragment())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {//создаём меню
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {//Когда выбран элемент из меню
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}