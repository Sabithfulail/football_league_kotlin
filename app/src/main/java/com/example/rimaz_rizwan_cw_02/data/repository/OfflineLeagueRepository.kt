package com.example.rimaz_rizwan_cw_02.data.repository

import com.example.rimaz_rizwan_cw_02.data.dao.ClubDao
import com.example.rimaz_rizwan_cw_02.data.dao.LeagueDao
import com.example.rimaz_rizwan_cw_02.data.entity.Club
import com.example.rimaz_rizwan_cw_02.data.entity.League
import kotlinx.coroutines.flow.Flow

class OfflineLeagueRepository(private val itemDao: LeagueDao, private val clubDao: ClubDao) :
    LeagueRepository {
    override fun getAllLeagueStream(): Flow<List<League>> = itemDao.getAllItems()

    override suspend fun insertListOfLeague(league: List<League>) =
        itemDao.insertListOfLeagues(league)

    override suspend fun insertListOfClub(clubs: List<Club>) = clubDao.insertListOfClub(clubs)

}
