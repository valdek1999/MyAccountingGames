package com.example.myaccounting.screens.game_edit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myaccounting.database.MainDatabaseDao
import com.example.myaccounting.screens.game.GameViewModel



@Suppress("UNCHECKED_CAST")
class GameEditViewModelFactory(
    private val dao: MainDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameEditViewModel::class.java)) {
            return GameEditViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}