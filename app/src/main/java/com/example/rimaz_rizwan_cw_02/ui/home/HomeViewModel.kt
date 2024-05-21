package com.example.rimaz_rizwan_cw_02.ui.home

import androidx.lifecycle.ViewModel
import com.example.rimaz_rizwan_cw_02.data.repository.OfflineLeagueRepository
import com.example.rimaz_rizwan_cw_02.data.entity.League

class HomeViewModel(private val repository: OfflineLeagueRepository) : ViewModel() {
    suspend fun saveListOfLeagues() {
        val leagues = listOf(
            League(4328, "English Premier League", "Soccer", "Premier League, EPL"),
            League(4329, "English League Championship", "Soccer", "Championship"),
            League(4330, "Scottish Premier League", "Soccer", "Scottish Premiership, SPFL"),
            League(4331, "German Bundesliga", "Soccer", "Bundesliga, Fußball-Bundesliga"),
            League(4332, "Italian Serie A", "Soccer", "Serie A"),
            League(4333, "French Ligue 1", "Soccer", "Ligue 1 Conforama"),
            League(4334, "Spanish La Liga", "Soccer", "LaLiga Santander, La Liga"),
            League(4335, "Greek Superleague Greece", "Soccer", ""),
            League(4336, "Dutch Eredivisie", "Soccer", "Eredivisie"),
            League(4337, "Belgian First Division A", "Soccer", "Jupiler Pro League"),
            League(4338, "Turkish Super Lig", "Soccer", "Super Lig"),
            League(4339, "Danish Superliga", "Soccer", ""),
            League(4340, "Portuguese Primeira Liga", "Soccer", "Liga NOS"),
            League(4341, "", "Soccer", ""),
            League(4342, "American Major League Soccer", "Soccer", "MLS, Major League Soccer"),
            League(4343, "Swedish Allsvenskan", "Soccer", "Fotbollsallsvenskan"),
            League(4344, "Mexican Primera League", "Soccer", "Liga MX"),
            League(4345, "Brazilian Serie A", "Soccer", ""),
            League(4346, "Ukrainian Premier League", "Soccer", ""),
            League(4347, "Russian Football Premier League", "Soccer", "Чемпионат России по футболу"),
            League(4348, "Australian A-League", "Soccer", "A-League"),
            League(4349, "Norwegian Eliteserien", "Soccer", "Eliteserien"),
            League(4350, "Chinese Super League", "Soccer", ""),
        )
        repository.insertListOfLeague(leagues)
    }
}