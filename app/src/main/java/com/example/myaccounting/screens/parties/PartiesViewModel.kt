package com.example.myaccounting.screens.parties

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myaccounting.database.Game
import com.example.myaccounting.database.MainDatabaseDao
import com.example.myaccounting.database.Party
import kotlinx.coroutines.*

class PartiesViewModel(
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

    private val _navigateTracker = MutableLiveData<Boolean>()
    val navigateTracker: LiveData<Boolean?>
        get() = _navigateTracker


    init {//чтобы понимать когда создаётся объект
        initializeListOfGames()
        Log.i("GameViewModel", "init")
    }

    private fun initializeListOfGames() {
        uiScope.launch {
            _parties_list.value = getListOfPartiesFromDatabase()
            _navigateTracker.value = true
        }
    }

    fun doneNavigating() {
        _navigateTracker.value = false
    }

    private suspend fun getListOfPartiesFromDatabase(): MutableList<Party>? {
        return withContext(Dispatchers.IO) {
            var parties = dao.getPartyFullList()
            parties
        }
    }

    fun deleteGame(party: Party) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                dao.deleteParty(party)
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