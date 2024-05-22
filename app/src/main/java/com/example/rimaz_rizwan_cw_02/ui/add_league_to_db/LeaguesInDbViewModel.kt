package com.example.rimaz_rizwan_cw_02.ui.add_league_to_db

import androidx.lifecycle.ViewModel
import com.example.rimaz_rizwan_cw_02.data.entity.League
import com.example.rimaz_rizwan_cw_02.data.repository.OfflineLeagueRepository
import kotlinx.coroutines.flow.Flow

class LeaguesInDbViewModel(private val repository: OfflineLeagueRepository) : ViewModel() {
    fun getAllLeagues(): Flow<List<League>> = repository.getAllLeagueStream()

}