package com.example.rimaz_rizwan_cw_02.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rimaz_rizwan_cw_02.data.entity.Club
import com.example.rimaz_rizwan_cw_02.data.entity.League
import kotlinx.coroutines.flow.Flow

@Dao
interface ClubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(club: Club)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfClub(clubs: List<Club>)

    @Query("SELECT * from club ORDER BY strLeague ASC")
    fun getAllClubs(): List<Club>

    @Query("""
        SELECT * FROM club
        WHERE strLeague LIKE '%' || :query || '%'
        OR strCountry LIKE '%' || :query || '%'
    """)
    fun searchClubs(query: String): Flow<List<Club>>

    @Query("""
        SELECT * FROM leagues
        WHERE strLeague LIKE '%' || :query || '%'
        OR strLeagueAlternate LIKE '%' || :query || '%'
    """)
    fun searchLeagues(query: String): Flow<List<League>>

}