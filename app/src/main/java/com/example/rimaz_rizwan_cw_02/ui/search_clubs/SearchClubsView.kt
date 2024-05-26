package com.example.rimaz_rizwan_cw_02.ui.search_clubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rimaz_rizwan_cw_02.FootBallTopAppBar
import com.example.rimaz_rizwan_cw_02.R
import com.example.rimaz_rizwan_cw_02.components.CustomButton
import com.example.rimaz_rizwan_cw_02.data.entity.Club
import com.example.rimaz_rizwan_cw_02.data.entity.League
import com.example.rimaz_rizwan_cw_02.ui.AppViewModelProvider
import com.example.rimaz_rizwan_cw_02.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object SearchClubsViewDestination : NavigationDestination {
    override val route = "search_clubs"
    override val titleRes = R.string.search_clubs
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchClubsViewScreen(
    navController: NavController,
    viewModel: SearchClubsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    var searchResults by remember { mutableStateOf<List<Any>>(emptyList()) }

    Column(modifier = Modifier.fillMaxSize()) {
        FootBallTopAppBar(
            title = stringResource(id = SearchClubsViewDestination.titleRes),
            canNavigateBack = true,
            navigateUp = { navController.navigateUp() }
        )
        var query by remember {
            mutableStateOf("")
        }
        TextField(
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            placeholder = { Text(text = "Search") },
            shape = RoundedCornerShape(15.dp), // Custom border radius,
            value = query, onValueChange = { query = it },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth()
                .background(color = Color(0xFFE1BEE7), shape = RoundedCornerShape(10.dp)),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        CustomButton(
            title = "Search",
            enabled = query.isNotEmpty(),
            onClick = {
                coroutineScope.launch {
                    viewModel.search(query).collect { results ->
                        searchResults = results
                    }
                }
            }
        )
        LazyColumn(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.padding_double_extra_large))
        ) {
            items(searchResults) { item ->
                when (item) {
                    is Club -> ClubItem(club = item)
                    is League -> LeagueItem(league = item)
                }
            }
        }


    }
}

@Composable
fun ClubItem(club: Club) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = club.strLeague, style = MaterialTheme.typography.titleLarge)
        Text(text = club.strLeagueAlternate, style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
fun LeagueItem(league: League) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = league.strLeague, style = MaterialTheme.typography.titleLarge)
        Text(text = league.strLeagueAlternate, style = MaterialTheme.typography.bodyLarge)
    }
}