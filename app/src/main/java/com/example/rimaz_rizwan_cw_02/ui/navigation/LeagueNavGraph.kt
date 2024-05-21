package com.example.rimaz_rizwan_cw_02.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rimaz_rizwan_cw_02.ui.add_league_to_db.LeaguesInDbDestination
import com.example.rimaz_rizwan_cw_02.ui.add_league_to_db.LeaguesInDbScreen

import com.example.rimaz_rizwan_cw_02.ui.home.HomeDestination
import com.example.rimaz_rizwan_cw_02.ui.home.HomeScreen
import com.example.rimaz_rizwan_cw_02.ui.search_club_by_league.SearchClubByLeagueScreen
import com.example.rimaz_rizwan_cw_02.ui.search_club_by_league.SearchClubByLgDestination

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun LeaguesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController, startDestination = HomeDestination.route, modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToAddLeagues = {navController.navigate(LeaguesInDbDestination.route)},
                searchForClubs = { navController.navigate(SearchClubByLgDestination.route) },
                searchForClubsByLeague = {navController.navigate(SearchClubByLgDestination.route)},
            )
        }
        composable(route = LeaguesInDbDestination.route) {
            LeaguesInDbScreen(
                navController = navController
            )
        }
        composable(route = SearchClubByLgDestination.route) {
            SearchClubByLeagueScreen(
                navController = navController
            )
        }
    }
}
