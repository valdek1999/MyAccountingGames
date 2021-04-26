package com.example.myaccounting.screens.game

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myaccounting.database.Game
import com.example.myaccounting.database.MainDatabaseDao
import kotlinx.coroutines.*

class GameViewModel(
    val dao: MainDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)//запускать на главном потоке, а потом выводить фрагмент

    init {//чтобы понимать когда создаётся объект
        Log.i("GameViewModel", "init")
    }

    fun addNewGame(name: String, count: Int) {
        uiScope.launch {
            val newGame = Game()
            newGame.gameName = name
            newGame.countOfPlayers = count
            insertDatabase(newGame)
        }
    }

    private suspend fun insertDatabase(element: Game) {
        withContext(Dispatchers.IO) {
            dao.insertGame(element)
        }
    }

}