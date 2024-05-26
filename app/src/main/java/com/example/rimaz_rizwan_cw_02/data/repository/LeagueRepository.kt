package com.example.rimaz_rizwan_cw_02.data.repository

import com.example.rimaz_rizwan_cw_02.data.entity.Club
import com.example.rimaz_rizwan_cw_02.data.entity.League
import kotlinx.coroutines.flow.Flow

interface LeagueRepository {
    /**
     * Retrieve all the league from the the given data source.
     */
    fun getAllLeagueStream(): Flow<List<League>>


    suspend fun insertListOfLeague(league: List<League>)
    suspend fun insertListOfClub(clubs: List<Club>)

    suspend fun searchClubs(query: String): Flow<List<Club>>
    suspend fun searchLeagues(query: String): Flow<List<League>>
}