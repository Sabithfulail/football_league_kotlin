package com.example.rimaz_rizwan_cw_02.ui.add_league_to_db

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.rimaz_rizwan_cw_02.R
import com.example.rimaz_rizwan_cw_02.data.League
import com.example.rimaz_rizwan_cw_02.ui.AppViewModelProvider
import com.example.rimaz_rizwan_cw_02.ui.navigation.NavigationDestination

object LeaguesInDbDestination : NavigationDestination {
    override val route = "leagues_in_db"
    override val titleRes = R.string.leagues_view_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaguesInDbScreen(
    navController: NavHostController,
    viewModel: LeaguesInDbViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val leaguesInDb by viewModel.getAllLeagues().collectAsState(initial = emptyList())
    Column {
        TopAppBar(
            title = { Text(text = stringResource(id = LeaguesInDbDestination.titleRes)) },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        leaguesInDbViewBody(
            leagueList = leaguesInDb
        )
    }
}

@Composable
fun leaguesInDbViewBody(
    leagueList: List<League>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (leagueList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_league_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        } else {

            LeagueList(
                leagueList = leagueList,
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
private fun LeagueList(
    leagueList: List<League>,
    onItemClick: (League) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(items = leagueList, key = { it.idLeague }) { item ->
            FootBallLeague(league = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item) })
        }

    }
}

@Composable
private fun FootBallLeague(
    league: League, modifier: Modifier = Modifier
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
                    text = league.strLeague,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = league.strSport,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = league.strLeagueAlternate,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
