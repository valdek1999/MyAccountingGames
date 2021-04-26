package com.example.myaccounting.screens.party

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myaccounting.database.MainDatabaseDao
import com.example.myaccounting.screens.games.GamesViewModel

@Suppress("UNCHECKED_CAST")
class PartyViewModelFactory(
    private val dao: MainDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PartyViewModel::class.java)) {
            return PartyViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}