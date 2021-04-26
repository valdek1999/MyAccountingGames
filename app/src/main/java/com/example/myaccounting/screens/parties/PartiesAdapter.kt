package com.example.myaccounting.screens.parties

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myaccounting.R
import com.example.myaccounting.database.Game
import com.example.myaccounting.database.MainDatabase
import com.example.myaccounting.database.Party
import com.example.myaccounting.screens.games.GamesFragmentDirections
import com.example.myaccounting.screens.games.GamesViewHolder
import com.example.myaccounting.screens.games.GamesViewModel
import com.example.myaccounting.screens.games.GamesViewModelFactory


class PartiesAdapter : RecyclerView.Adapter<PartiesViewHolder>() {

    var data = listOf<Party>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartiesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item,parent,false)

        return PartiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartiesViewHolder, position: Int) {
        val item = data[position]
        holder.addPartyImageView.visibility = View.GONE
        holder.titleTextView.text = "Id party: " +item.partyId.toString()
        holder.titleTextView.text = holder.titleTextView.text.toString() + "\nGame:${item.gameName}"
        var info = ""
        var scores = item.listScores.split('|')
        var players = item.listPlayers.split('|')
        var i = 0
        var count = 0
        for (el in players)
        {
            if(el == "")
            {
                i++
                continue
            }
            else
            {
                info += "Player:${el.toString()} Points: ${scores[i]}\n"
                i++
                count ++
            }

        }
        info += "Winner: ${item.winPlayer}"
        holder.infoTextView.text = info

        holder.editImage.setOnClickListener {
            Navigation.findNavController(it).navigate(
                PartiesFragmentDirections.actionPartiesFragmentToPartyEditFragment(
                    item.partyId,count,item.gameName,item.gameId,item.listPlayers,item.listScores))
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}