package com.example.myaccounting.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.myaccounting.R
import com.example.myaccounting.database.Game
import com.example.myaccounting.database.MainDatabase
import com.example.myaccounting.databinding.FragmentGameBinding
import com.example.myaccounting.screens.game.GameViewModel
import com.example.myaccounting.screens.game.GameViewModelFactory
import com.example.myaccounting.screens.games.GamesFragmentDirections
import com.example.myaccounting.screens.games.GamesViewModel

class GameFragment : Fragment() {
    lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    private fun checkData(): Boolean {
        return binding.editNameGame.text.toString() != "" && binding.countPlayers.progress >=2 && binding.countPlayers.progress <=5
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_game, container, false)

        val application = requireNotNull(this.activity).application
        val dao = MainDatabase.getInstance(application).getMainDatabaseDao()
        val viewModelFactory = GameViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(GameViewModel::class.java)

        binding.textView3.text = "Count of players: ${binding.countPlayers.progress.toString()}"

        binding.countPlayers.setOnSeekBarChangeListener(object :

            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int,
                fromUser: Boolean){
                binding.textView3.text = "Count of players: ${binding.countPlayers.progress.toString()}"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                binding.textView3.text = "Count of players: ${binding.countPlayers.progress.toString()}"
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                binding.textView3.text = "Count of players: ${binding.countPlayers.progress.toString()}"
            }
        })

        binding.buttonAccept.setOnClickListener {
            if (checkData()) {
                viewModel.addNewGame(
                    binding.editNameGame.text.toString(),
                    binding.countPlayers.progress
                )
                this.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGamesFragment())
            } else {
                Toast.makeText(requireActivity(), "Данные неверны", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}