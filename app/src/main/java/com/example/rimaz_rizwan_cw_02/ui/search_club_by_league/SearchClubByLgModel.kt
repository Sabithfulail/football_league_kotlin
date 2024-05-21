//package com.example.rimaz_rizwan_cw_02.ui.search_club_by_league
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.rimaz_rizwan_cw_02.data.repository.OfflineLeagueRepository
//import com.example.rimaz_rizwan_cw_02.data.club.Club
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import java.net.URL
//
//
//class SearchClubByLgModel(private val repository: OfflineLeagueRepository) : ViewModel() {
//    private val baseUrl = "https://www.thesportsdb.com/api/v1/json/3/search_all_leagues.php"
//
//    fun searchClubsByLeague(leagueName: String, onComplete: (List<Club>) -> Unit) {
//        val url = "$baseUrl?c=England&s=$leagueName"
//
//        viewModelScope.launch {
//            val result = fetchData(url)
//            onComplete(result)
//        }
//    }
//
//    private suspend fun fetchData(urlString: String): List<Club> {
//        return withContext(Dispatchers.IO) {
//            val url = URL(urlString)
//            val jsonString = url.readText()
//            // Parse JSON string and return list of clubs
//            // Example: Gson().fromJson(jsonString, ClubList::class.java).clubs
//            // Make sure to replace ClubList with your actual data model
//            // and adjust parsing accordingly.
//            emptyList() // Placeholder
//        }
//    }
//}