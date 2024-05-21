package com.example.rimaz_rizwan_cw_02.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [League::class], version = 1, exportSchema = false)
abstract class LeagueDatabase : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao

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

