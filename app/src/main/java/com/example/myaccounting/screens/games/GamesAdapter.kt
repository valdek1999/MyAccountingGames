package com.example.myaccounting.screens.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myaccounting.R
import com.example.myaccounting.database.Game

class GamesAdapter : RecyclerView.Adapter<GamesViewHolder>() {

    var data = listOf<Game>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item,parent,false)

        return GamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val item = data[position]
        holder.titleTextView.text = item.gameName.toString()
        holder.infoTextView.text = "Count of players " + item.countOfPlayers.toString()
        holder.editImage.setOnClickListener {
            Navigation.findNavController(it).navigate(GamesFragmentDirections.actionGamesFragmentToGameEditFragment(item.gameId))
        }
        holder.addPartyImageView.setOnClickListener {
            Navigation.findNavController(it).navigate(
                GamesFragmentDirections.
                actionGamesFragmentToPartyFragment
                    (gameId =  item.gameId, gameName =  item.gameName, countOfPlayers =  item.countOfPlayers))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}