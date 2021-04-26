package com.example.myaccounting.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Game::class, Party::class],
    version = 2, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    abstract fun getMainDatabaseDao(): MainDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        MainDatabase::class.java, "main_db").fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}