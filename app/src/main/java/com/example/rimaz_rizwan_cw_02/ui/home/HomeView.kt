package com.example.rimaz_rizwan_cw_02.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rimaz_rizwan_cw_02.R
import com.example.rimaz_rizwan_cw_02.components.CustomButton
import com.example.rimaz_rizwan_cw_02.ui.AppViewModelProvider
import com.example.rimaz_rizwan_cw_02.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun HomeScreen(
    navigateToAddLeagues: () -> Unit,
    searchForClubsByLeague: () -> Unit,
    searchForClubs: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val coroutineScope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.Center,

        ) {
        CustomButton(
            enabled = true,
            onClick = {
                navigateToAddLeagues()
                coroutineScope.launch {
                    viewModel.saveListOfLeagues()
                }
            },
            title = "Add Leagues to Db"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(
            enabled = true,
            onClick = { searchForClubsByLeague() },
            title = "Search clubs by league"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(
            enabled = true,
            onClick = { searchForClubs() },
            title = "Search clubs"
        )
    }
}
