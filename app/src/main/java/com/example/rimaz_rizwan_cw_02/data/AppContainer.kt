
package com.example.rimaz_rizwan_cw_02.data

import android.content.Context

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
        OfflineLeagueRepository(LeagueDatabase.getDatabase(context).leagueDao())
    }
    override val offlineLeagueRepository: OfflineLeagueRepository by lazy {
        OfflineLeagueRepository(LeagueDatabase.getDatabase(context).leagueDao())
    }
}
