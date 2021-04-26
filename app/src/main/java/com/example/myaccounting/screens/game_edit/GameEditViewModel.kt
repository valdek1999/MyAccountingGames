package com.example.myaccounting.screens.game_edit

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.myaccounting.database.Game
import com.example.myaccounting.database.MainDatabaseDao
import kotlinx.coroutines.*

class GameEditViewModel(
    val dao: MainDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)//запускать на главном потоке, а потом выводить фрагмент

    init {//чтобы понимать когда создаётся объект
        Log.i("GameViewModel", "init")
    }

    fun UpdateGame(name: String, count: Int, id: Long) {
        uiScope.launch {
            val newGame = Game()
            newGame.gameId = id
            newGame.gameName = name
            newGame.countOfPlayers = count
            insertDatabase(newGame)
        }
    }

    fun DeleteGame(id: Long) {
        uiScope.launch {
            val newGame = Game()
            newGame.gameId = id
            deleteDatabase(newGame)
        }
    }

    private suspend fun insertDatabase(element: Game) {
        withContext(Dispatchers.IO) {
            dao.updateGame(element)
        }
    }


    private suspend fun deleteDatabase(element: Game) {
        withContext(Dispatchers.IO) {
            dao.deleteGame(element)
        }
    }
}