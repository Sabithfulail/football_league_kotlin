package com.example.rimaz_rizwan_cw_02.ui.search_clubs

import androidx.lifecycle.ViewModel
import com.example.rimaz_rizwan_cw_02.data.entity.Club
import com.example.rimaz_rizwan_cw_02.data.entity.League
import com.example.rimaz_rizwan_cw_02.data.repository.OfflineLeagueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchClubsViewModel(private val repository: OfflineLeagueRepository) : ViewModel() {
    private val clubList = mutableListOf<Club>()
    private val _clubListStateFlow = MutableStateFlow<List<Club>>(emptyList())
    val clubListStateFlow: StateFlow<List<Club>> = _clubListStateFlow

    fun getAllLeagues(): Flow<List<League>> = repository.getAllLeagueStream()
}