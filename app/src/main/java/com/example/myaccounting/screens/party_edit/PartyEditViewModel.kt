package com.example.myaccounting.screens.party_edit

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.myaccounting.database.Game
import com.example.myaccounting.database.MainDatabaseDao
import com.example.myaccounting.database.Party
import kotlinx.coroutines.*

class PartyEditViewModel(
    val dao: MainDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)//запускать на главном потоке, а потом выводить фрагмент


    fun addNewParty(id: Long,Players: String, Scores: String, GameId:Long, GameName:String, WinPlayer:String) {
        uiScope.launch {
            val newParty = Party()
            newParty.partyId = id
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
            dao.updateParty(element)
        }
    }
    fun DeleteGame(id: Long) {
        uiScope.launch {
            val newParty = Party()
            newParty.partyId = id
            deleteDatabase(newParty)
        }
    }
    private suspend fun deleteDatabase(element: Party) {
        withContext(Dispatchers.IO) {
            dao.deleteParty(element)
        }
    }




}