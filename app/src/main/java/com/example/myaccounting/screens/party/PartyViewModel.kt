package com.example.myaccounting.screens.party

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.myaccounting.database.Game
import com.example.myaccounting.database.MainDatabaseDao
import com.example.myaccounting.database.Party
import kotlinx.coroutines.*

class PartyViewModel(
    val dao: MainDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)//запускать на главном потоке, а потом выводить фрагмент


    fun addNewPlayer(Players: String, Scores: String, GameId:Long, GameName:String, WinPlayer:String) {
        uiScope.launch {
            val newParty = Party()
            newParty.listPlayers = Players
            newParty.listScores = Scores
            newParty.gameId = GameId
            newParty.winPlayer = WinPlayer
            newParty.gameName = GameName
            insertDatabase(newParty)
        }
    }

    private suspend fun insertDatabase(element: Party) {
        withContext(Dispatchers.IO) {
            dao.insertParty(element)
        }
    }




}