package com.example.rimaz_rizwan_cw_02.ui.search_clubs

import androidx.lifecycle.ViewModel
import com.example.rimaz_rizwan_cw_02.data.repository.OfflineLeagueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class SearchClubsViewModel(private val repository: OfflineLeagueRepository) : ViewModel() {
    suspend fun search(query: String): Flow<List<Any>> {
        return combine(
            repository.searchClubs(query),
            repository.searchLeagues(query)
        ) { clubs, leagues ->
            val results = mutableListOf<Any>()
            results.addAll(clubs)
            results.addAll(leagues)
            results
        }
    }
}