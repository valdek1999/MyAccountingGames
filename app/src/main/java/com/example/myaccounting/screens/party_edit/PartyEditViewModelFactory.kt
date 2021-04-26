package com.example.myaccounting.screens.party_edit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myaccounting.database.MainDatabaseDao

@Suppress("UNCHECKED_CAST")
class PartyEditViewModelFactory(
    private val dao: MainDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PartyEditViewModel::class.java)) {
            return PartyEditViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}