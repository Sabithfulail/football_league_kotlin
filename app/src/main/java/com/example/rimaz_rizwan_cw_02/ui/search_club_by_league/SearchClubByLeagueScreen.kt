package com.example.rimaz_rizwan_cw_02.ui.search_club_by_league

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.rimaz_rizwan_cw_02.R
import com.example.rimaz_rizwan_cw_02.ui.AppViewModelProvider
import com.example.rimaz_rizwan_cw_02.ui.add_league_to_db.LeaguesInDbViewModel
import com.example.rimaz_rizwan_cw_02.ui.add_league_to_db.leaguesInDbViewBody
import com.example.rimaz_rizwan_cw_02.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object SearchClubByLgDestination : NavigationDestination {
    override val route = "search_club_by_league"
    override val titleRes = R.string.search_club_by_league_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchClubByLeagueScreen(
    navController: NavHostController,
    viewModel: LeaguesInDbViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val retrievedClubs = viewModel.getLeagueList()
    val leagues by viewModel.leaguesListStateFlow.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val coroutineScope = rememberCoroutineScope()

        TopAppBar(
            title = { Text(text = stringResource(id = SearchClubByLgDestination.titleRes)) },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        var leagueName by remember { mutableStateOf("") }
        TextField(
            value = leagueName,
            onValueChange = { leagueName = it },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth()
                .background(color = Color(0xFFE1BEE7), shape = RoundedCornerShape(10.dp))


        )

        Button(
            enabled = leagueName.isNotEmpty(),
            onClick = {
                coroutineScope.launch {
                    if (leagueName.isNotEmpty()) {
                        coroutineScope.launch {
                            viewModel.fetchLeagues(leagueName)
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Retrieve Clubs")
        }
        Button(
            onClick = {
                coroutineScope.launch {

                }
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Save Clubs")
        }
        if (leagues.isNotEmpty())
            leaguesInDbViewBody(
                leagueList = leagues
            )


    }
}



