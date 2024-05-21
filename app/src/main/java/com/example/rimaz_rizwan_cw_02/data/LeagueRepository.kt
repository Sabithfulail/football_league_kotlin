package com.example.rimaz_rizwan_cw_02.data

import kotlinx.coroutines.flow.Flow

interface LeagueRepository {
    /**
     * Retrieve all the league from the the given data source.
     */
    fun getAllLeagueStream(): Flow<List<League>>

    /**
     * Retrieve an league from the given data source that matches with the [id].
     */
    fun getLeagueStream(id: Int): Flow<League?>

    /**
     * Insert league in the data source
     */
    suspend fun insertLeague(league: League)


    suspend fun insertListOfLeague(league: List<League>)

    /**
     * Delete league from the data source
     */
    suspend fun deleteLeague(league: League)

    /**
     * Update league in the data source
     */
    suspend fun updateLeague(league: League)


    suspend fun getAllLeague(): List<League>
}