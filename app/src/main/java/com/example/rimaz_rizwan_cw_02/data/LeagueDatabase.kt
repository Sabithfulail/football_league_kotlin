package com.example.rimaz_rizwan_cw_02.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rimaz_rizwan_cw_02.data.dao.ClubDao
import com.example.rimaz_rizwan_cw_02.data.dao.LeagueDao
import com.example.rimaz_rizwan_cw_02.data.entity.Club
import com.example.rimaz_rizwan_cw_02.data.entity.League

@Database(entities = [League::class, Club::class], version = 1, exportSchema = false)
abstract class LeagueDatabase : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao
    abstract fun clubDao(): ClubDao

    companion object {
        @Volatile
        private var Instance: LeagueDatabase? = null

        fun getDatabase(context: Context): LeagueDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, LeagueDatabase::class.java, "item_database")
                    .build().also { Instance = it }
            }
        }
    }
}

