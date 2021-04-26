package com.example.myaccounting.database

import android.media.AsyncPlayer
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class Game(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gameId: Long = 0L,

    @ColumnInfo(name = "name")
    var gameName: String = "",

    @ColumnInfo(name = "count_of_players")
    var countOfPlayers: Int = 2
)