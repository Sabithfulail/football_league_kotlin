package com.example.rimaz_rizwan_cw_02.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rimaz_rizwan_cw_02.data.entity.League
import kotlinx.coroutines.flow.Flow

@Dao
interface LeagueDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(league: League)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListOfLeagues(leagues: List<League>)

    @Update
    suspend fun update(league: League)

    @Delete
    suspend fun delete(league: League)

    @Query("SELECT * from leagues WHERE idLeague = :id")
    fun getItem(id: Int): Flow<League>

    @Query("SELECT * from leagues ORDER BY strLeague ASC")
    fun getAllLeagues(): Flow<List<League>>

    @Query("SELECT * from leagues ORDER BY strLeague ASC")
    fun getAllLeague(): List<League>
}