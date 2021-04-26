package com.example.myaccounting.screens.game_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myaccounting.R
import com.example.myaccounting.database.MainDatabase
import com.example.myaccounting.databinding.FragmentGameEditBinding

class GameEditFragment : Fragment() {
    lateinit var viewModel: GameEditViewModel

    private lateinit var binding: FragmentGameEditBinding

    private fun checkData(): Boolean {
        return binding.editNameGame.text.toString() != "" && binding.countPlayers.progress >=2 && binding.countPlayers.progress <=5
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_game_edit, container, false)
        val args = GameEditFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(this.activity).application
        val dao = MainDatabase.getInstance(application).getMainDatabaseDao()
        val viewModelFactory = GameEditViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(GameEditViewModel::class.java)

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

        binding.buttonDelete.setOnClickListener {
            viewModel.DeleteGame(
                args.idGame
            )
            this.findNavController().navigate(GameEditFragmentDirections.actionGameEditFragmentToGamesFragment())
        }
        binding.buttonAccept.setOnClickListener {
            if (checkData()) {
                viewModel.UpdateGame(
                    binding.editNameGame.text.toString(),
                    binding.countPlayers.progress,
                    args.idGame
                )
                this.findNavController().navigate(GameEditFragmentDirections.actionGameEditFragmentToGamesFragment())
            } else {
                Toast.makeText(requireActivity(), "Данные неверны", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}