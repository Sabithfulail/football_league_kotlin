package com.example.rimaz_rizwan_cw_02.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
data class League(
    @PrimaryKey(autoGenerate = true)
    val idLeague: Int = 0,
    val strLeague: String,
    val strSport: String,
    val strLeagueAlternate: String
)
