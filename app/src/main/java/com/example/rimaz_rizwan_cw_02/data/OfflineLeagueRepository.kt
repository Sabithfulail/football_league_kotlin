package com.example.rimaz_rizwan_cw_02.data

import kotlinx.coroutines.flow.Flow

class OfflineLeagueRepository(private val itemDao: LeagueDao) : LeagueRepository {
    override fun getAllLeagueStream(): Flow<List<League>> = itemDao.getAllItems()

    override fun getLeagueStream(id: Int): Flow<League?> = itemDao.getItem(id)

    override suspend fun insertLeague(league: League) = itemDao.insert(league)


    override suspend fun insertListOfLeague(league: List<League>) = itemDao.insertListOfLeagues(league)

    override suspend fun deleteLeague(league: League) = itemDao.delete(league)

    override suspend fun updateLeague(league: League) = itemDao.update(league)

    override suspend fun getAllLeague(): List<League> {
        return itemDao.getAllLeague()
    }
}
