package com.example.myaccounting.screens.party_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myaccounting.R
import com.example.myaccounting.database.MainDatabase
import com.example.myaccounting.databinding.FragmentPartyBinding

class PartyEditFragment: Fragment() {
    lateinit var editViewModel: PartyEditViewModel

    private lateinit var binding: FragmentPartyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_party, container, false)
        val args = PartyEditFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(this.activity).application
        val dao = MainDatabase.getInstance(application).getMainDatabaseDao()
        val viewModelFactory = PartyEditViewModelFactory(dao, application)
        editViewModel = ViewModelProvider(this, viewModelFactory)
            .get(PartyEditViewModel::class.java)
        binding.nameGameText.text = args.gameName

        caseVisible(args)
        binding.buttonAccept.setOnClickListener {
            var strPlayers = "${binding.editName1.text}|${binding.editName2.text}|${binding.editName3.text}|" +
                    "${binding.editName4.text}|${binding.editName5.text}"
            var strScores = "${binding.editPoints1.text}|${binding.editPoints2.text}|${binding.editPoints3.text}|" +
                    "${binding.editPoints4.text}|${binding.editPoints5.text}"

            var winstr: String = binding.whoWin.text.toString()

            var check = false
            for(el in strPlayers.split('|'))
            {
                if (el.toString() == winstr.toString())
                {
                    editViewModel.addNewParty(Players = strPlayers, Scores = strScores, GameId = args.gameId,WinPlayer = winstr, GameName = args.gameName, id = args.idParty )
                    this.findNavController().navigate(PartyEditFragmentDirections.actionPartyEditFragmentToPartiesFragment())
                    break
                }
                else{
                    Toast.makeText(requireActivity(), "Name of winner has not been found ", Toast.LENGTH_SHORT).show()
                }

            }
        }
        return binding.root
    }



    private fun caseVisible(fragmentArgs: PartyEditFragmentArgs)
    {
        val players = fragmentArgs.players.split('|')
        val scores = fragmentArgs.scores.split('|')
        binding.editName1.setText(players[0])
        binding.editName2.setText(players[1])
        binding.editName3.setText(players[2])
        binding.editName4.setText(players[3])
        binding.editName5.setText(players[4])
        binding.editPoints1.setText(scores[0])
        binding.editPoints2.setText(scores[1])
        binding.editPoints3.setText(scores[2])
        binding.editPoints4.setText(scores[3])
        binding.editPoints5.setText(scores[4])

        when (fragmentArgs.countOfPlayers) {
            2 ->
            {   binding.editName1.visibility = View.VISIBLE
                binding.editPoints1.visibility = View.VISIBLE
                binding.editName2.visibility = View.VISIBLE
                binding.editPoints2.visibility = View.VISIBLE
            }
            3 ->
            {   binding.editName1.visibility = View.VISIBLE
                binding.editPoints1.visibility = View.VISIBLE
                binding.editName2.visibility = View.VISIBLE
                binding.editPoints2.visibility = View.VISIBLE
                binding.editName3.visibility = View.VISIBLE
                binding.editPoints3.visibility = View.VISIBLE
            }
            4 ->
            {   binding.editName1.visibility = View.VISIBLE
                binding.editPoints1.visibility = View.VISIBLE
                binding.editName2.visibility = View.VISIBLE
                binding.editPoints2.visibility = View.VISIBLE
                binding.editName3.visibility = View.VISIBLE
                binding.editPoints3.visibility = View.VISIBLE
                binding.editName4.visibility = View.VISIBLE
                binding.editPoints4.visibility = View.VISIBLE
            }
            5 ->
            {   binding.editName1.visibility = View.VISIBLE
                binding.editPoints1.visibility = View.VISIBLE
                binding.editName2.visibility = View.VISIBLE
                binding.editPoints2.visibility = View.VISIBLE
                binding.editName3.visibility = View.VISIBLE
                binding.editPoints3.visibility = View.VISIBLE
                binding.editName4.visibility = View.VISIBLE
                binding.editPoints4.visibility = View.VISIBLE
                binding.editName5.visibility = View.VISIBLE
                binding.editPoints5.visibility = View.VISIBLE
            }
            else -> {
            }
        }
    }
}