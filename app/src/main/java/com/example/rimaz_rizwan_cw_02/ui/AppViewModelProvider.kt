package com.example.rimaz_rizwan_cw_02.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rimaz_rizwan_cw_02.FootBallLeagueApplication
import com.example.rimaz_rizwan_cw_02.ui.add_league_to_db.LeaguesInDbViewModel
import com.example.rimaz_rizwan_cw_02.ui.home.HomeViewModel
import com.example.rimaz_rizwan_cw_02.ui.search_club_by_league.SearchClubByLgModel
import com.example.rimaz_rizwan_cw_02.ui.search_clubs.SearchClubsViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {

    val Factory = viewModelFactory {

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(leagueApplication().container.offlineLeagueRepository)
        }

        initializer {
            LeaguesInDbViewModel(leagueApplication().container.offlineLeagueRepository)
        }

        initializer {
            SearchClubByLgModel(leagueApplication().container.offlineLeagueRepository)
        }

        initializer {
            SearchClubsViewModel(leagueApplication().container.offlineLeagueRepository)
        }

    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [FootBallLeagueApplication].
 */
fun CreationExtras.leagueApplication(): FootBallLeagueApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FootBallLeagueApplication)
