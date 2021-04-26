package com.example.myaccounting.screens.games

import android.app.Application
import android.annotation.SuppressLint
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myaccounting.database.Game
import com.example.myaccounting.database.MainDatabaseDao
import kotlinx.coroutines.*

class GamesViewModel(
    val dao: MainDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)//запускать на главном потоке, а потом выводить фрагмент
    private var _games_list: MutableLiveData<MutableList<Game>> = MutableLiveData<MutableList<Game>>()
    private val _navigateTracker = MutableLiveData<Boolean>()
    val navigateTracker: LiveData<Boolean?>
        get() = _navigateTracker
    val games_list: LiveData<MutableList<Game>>
        get() = _games_list

    init {//чтобы понимать когда создаётся объект
        initializeListOfGames()
        Log.i("GameViewModel", "init")
    }
    private fun initializeListOfGames() {
        uiScope.launch {
            _games_list.value = getListOfGamesFromDatabase()
            _navigateTracker.value = true
        }
    }
    fun doneNavigating() {
        _navigateTracker.value = false
    }

    private suspend fun getListOfGamesFromDatabase(): MutableList<Game>? {
        return withContext(Dispatchers.IO) {
            var games = dao.getGameFullList()
            games
        }
    }
    fun deleteGame(game: Game) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                dao.deleteGame(game)
            }
        }
    }

    fun addGame(game: Game) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                dao.insertGame(game)
            }
        }
    }

}