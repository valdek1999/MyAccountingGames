package com.example.myaccounting.screens.winstats

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myaccounting.database.MainDatabaseDao
import com.example.myaccounting.screens.winstats.WinStatsViewModel

@Suppress("UNCHECKED_CAST")
class WinStatsViewModelFactory(
    private val dao: MainDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WinStatsViewModel::class.java)) {
            return WinStatsViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}