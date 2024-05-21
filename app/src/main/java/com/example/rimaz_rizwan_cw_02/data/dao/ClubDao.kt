package com.example.rimaz_rizwan_cw_02.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rimaz_rizwan_cw_02.data.entity.Club
@Dao
interface ClubDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(club: Club)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListOfClub(clubs: List<Club>)

    @Update
    suspend fun update(club: Club)

    @Delete
    suspend fun delete(club: Club)

    @Query("SELECT * from club ORDER BY strLeague ASC")
    fun getAllClubs(): List<Club>
}