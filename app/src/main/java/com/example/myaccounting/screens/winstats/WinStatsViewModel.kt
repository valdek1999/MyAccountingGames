package com.example.myaccounting.screens.winstats

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myaccounting.database.Game
import com.example.myaccounting.database.MainDatabaseDao
import com.example.myaccounting.database.Party
import kotlinx.coroutines.*

class WinStatsViewModel (
    val dao: MainDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope =
        CoroutineScope(Dispatchers.Main + viewModelJob)//запускать на главном потоке, а потом выводить фрагмент

    private var _parties_list: MutableLiveData<MutableList<Party>> =
        MutableLiveData<MutableList<Party>>()
    val parties_list: LiveData<MutableList<Party>>
        get() = _parties_list

    private var _games_list: MutableLiveData<MutableList<Game>> = MutableLiveData<MutableList<Game>>()
    val games_list: LiveData<MutableList<Game>>
        get() = _games_list

    private val _navigateTracker = MutableLiveData<Boolean>()
    val navigateTracker: LiveData<Boolean?>
        get() = _navigateTracker


    public fun SearchListOfGames(name: String) {
        uiScope.launch {
            _parties_list.value = getListOfPartiesFromDatabase(name)
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
    private suspend fun getListOfPartiesFromDatabase(name: String): MutableList<Party>? {
        return withContext(Dispatchers.IO) {
            var parties = dao.findPartiesOfPlayer(name)
            parties
        }
    }
}