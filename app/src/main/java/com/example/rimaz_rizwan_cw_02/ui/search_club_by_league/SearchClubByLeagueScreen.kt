package com.example.rimaz_rizwan_cw_02.ui.search_club_by_league

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.rimaz_rizwan_cw_02.R
import com.example.rimaz_rizwan_cw_02.data.entity.Club
import com.example.rimaz_rizwan_cw_02.ui.AppViewModelProvider
import com.example.rimaz_rizwan_cw_02.ui.add_league_to_db.LeaguesInDbViewModel
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
    val clubs by viewModel.clubListStateFlow.collectAsState()
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
                            viewModel.fetchClubs(leagueName)
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
                    if (clubs.isNotEmpty()){
                        viewModel.saveListOfClubs(clubs)
                    }
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Save Clubs")
        }
        if (clubs.isNotEmpty())
            ClubsInDbViewBody(
                clubList = clubs
            )


    }
}


@Composable
fun ClubsInDbViewBody(
    clubList: List<Club>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (clubList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_league_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        } else {

            ClubList(
                clubList = clubList,
                onItemClick = { ///Todo :
                },
                contentPadding = contentPadding,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                    .padding(bottom = dimensionResource(id = R.dimen.padding_double_extra_large))
//                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )

        }

    }
}


@Composable
private fun ClubList(
    clubList: List<Club>,
    onItemClick: (Club) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(items = clubList, key = { it.idLeague }) { item ->
            FootBallClub(club = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item) })
        }

    }
}


@Composable
private fun FootBallClub(
    club: Club, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = club.strLeague,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = club.strSport,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = club.strLeagueAlternate,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
