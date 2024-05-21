package com.example.rimaz_rizwan_cw_02.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rimaz_rizwan_cw_02.R
import com.example.rimaz_rizwan_cw_02.ui.AppViewModelProvider
import com.example.rimaz_rizwan_cw_02.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToAddLeagues: () -> Unit,
    searchForClubsByLeague: () -> Unit,
    searchForClubs: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val coroutineScope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Button(
            onClick = {
                navigateToAddLeagues()
                coroutineScope.launch { viewModel.saveListOfLeagues() }

            },
            modifier.fillMaxWidth()
        ) {
            Text(text = "Add Leagues to Db")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                searchForClubsByLeague()
                      },
            modifier.fillMaxWidth()
        ) {
            Text(text = "Search clubs by league")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { searchForClubs()/*TODO*/ },
            modifier.fillMaxWidth()
        ) {
            Text(text = "Search clubs")
        }
    }
}
