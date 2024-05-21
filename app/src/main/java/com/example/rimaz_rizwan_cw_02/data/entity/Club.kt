package com.example.rimaz_rizwan_cw_02.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "club")
data class Club(
    @PrimaryKey
    val idLeague: String,
    val strSport: String,
    val strLeague: String,
    val strLeagueAlternate: String,
    val intDivision: String,
    val strCurrentSeason: String,
    val dateFirstEvent: String,
    val strGender: String,
    val strCountry: String,
)
