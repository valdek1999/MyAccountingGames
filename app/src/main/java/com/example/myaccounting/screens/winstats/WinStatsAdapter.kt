package com.example.myaccounting.screens.winstats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myaccounting.R
import com.example.myaccounting.database.Game
import com.example.myaccounting.database.Party
import kotlin.properties.Delegates


class WinStatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val editImage: ImageView = itemView.findViewById(R.id.edit_image)
    val titleTextView: TextView = itemView.findViewById(R.id.titleView)
    val infoTextView: TextView = itemView.findViewById(R.id.infoView)
    val addPartyImageView: ImageView = itemView.findViewById(R.id.addPartyImage)
}

class WinStatsAdapter: RecyclerView.Adapter<WinStatsViewHolder>() {
    var data_parties = listOf<Party>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var data_games = listOf<Game>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var data_name = String()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WinStatsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item,parent,false)

        return WinStatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: WinStatsViewHolder, position: Int) {
        val item = data_games[position]
        holder.addPartyImageView.visibility = View.GONE
        holder.editImage.visibility = View.GONE
        holder.titleTextView.text = "Game: " +item.gameName.toString()
        var percent = 0.0;
        var count_win = 0;
        var count_lose =0;
        for (party in data_parties )
        {
            if(party.gameName == item.gameName)
            {
                if(party.winPlayer == data_name)
                {
                    count_win++

                }
                else
                {
                    count_lose++
                }
            }
        }
        if(count_win+count_lose!=0)
        {
            percent = (count_win.toDouble()/(count_win+count_lose).toDouble()*100)
            holder.infoTextView.text = "Won:${count_win}\nLose:${count_lose}\nPercentage of games won:${percent} "
        }
        else
        {
            holder.infoTextView.text = "No games played"
        }
        /*
        var info = ""
        var scores = item.listScores.split('|')
        var players = item.listPlayers.split('|')
        var i = 0
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
            }

        }
        info += "Winner: ${item.winPlayer}"
        holder.infoTextView.text = info
        holder.editImage.setOnClickListener {
            Navigation.findNavController(it).navigate(
                WDirections.actionPartiesFragmentToGamesFragment())

        }*/

    }
    override fun getItemCount(): Int {
        return data_games.size
    }
}

