package com.example.rimaz_rizwan_cw_02.data

import android.content.Context
import com.example.rimaz_rizwan_cw_02.data.repository.LeagueRepository
import com.example.rimaz_rizwan_cw_02.data.repository.OfflineLeagueRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val leagueRepository: LeagueRepository
    val offlineLeagueRepository: OfflineLeagueRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val leagueRepository: LeagueRepository by lazy {
        OfflineLeagueRepository(
            LeagueDatabase.getDatabase(context).leagueDao(),
            LeagueDatabase.getDatabase(context).clubDao()
        )
    }
    override val offlineLeagueRepository: OfflineLeagueRepository by lazy {
        OfflineLeagueRepository(
            LeagueDatabase.getDatabase(context).leagueDao(),
            LeagueDatabase.getDatabase(context).clubDao()
        )
    }
}
