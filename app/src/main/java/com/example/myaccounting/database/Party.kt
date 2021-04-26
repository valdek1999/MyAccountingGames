package com.example.myaccounting.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "party_table")
data class Party(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var partyId: Long = 0L,

    @ColumnInfo(name = "game_id")
    var gameId: Long = 0L,

    @ColumnInfo(name = "game_name")
    var gameName: String = "",

    @ColumnInfo(name = "list_players")
    var listPlayers: String = "",

    @ColumnInfo(name = "list_scores")
    var listScores: String = "",

    @ColumnInfo(name = "win_player")
    var winPlayer: String = ""
)
