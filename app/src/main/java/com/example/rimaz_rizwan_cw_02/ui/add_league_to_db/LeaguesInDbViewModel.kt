package com.example.rimaz_rizwan_cw_02.ui.add_league_to_db

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rimaz_rizwan_cw_02.data.OfflineLeagueRepository
import com.example.rimaz_rizwan_cw_02.data.League
import com.example.rimaz_rizwan_cw_02.data.club.Club
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class LeaguesInDbViewModel(private val repository: OfflineLeagueRepository) : ViewModel() {
    fun getAllLeagues(): Flow<List<League>> = repository.getAllLeagueStream()

    private val leaguesList = mutableListOf<League>()

    private val _leaguesListStateFlow = MutableStateFlow<List<League>>(emptyList())
    val leaguesListStateFlow: StateFlow<List<League>> = _leaguesListStateFlow
    fun getLeagueList():List<League>{
        return leaguesList
    }
    suspend fun fetchLeagues(keyword: String): List<League> {
        val url_string =
            "https://www.thesportsdb.com/api/v1/json/3/search_all_leagues.php?c=$keyword&s=Soccer"
        println(url_string)
        val url = URL(url_string)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
// collecting all the JSON string
        var stb = StringBuilder()
// run the code of the launched coroutine in a new thread
        withContext(Dispatchers.IO) {
            var bf = BufferedReader(InputStreamReader(con.inputStream))
            var line: String? = bf.readLine()
            while (line != null) { // keep reading until no more lines of text
                stb.append(line + "\n")
                line = bf.readLine()
            }
        }
        val leagues = parseJSON(stb)
        leaguesList.addAll(leagues)
        _leaguesListStateFlow.value = leaguesList
        return leagues
    }

    fun parseJSON(stb: StringBuilder): List<League> {
        val searchResponse = JSONObject(stb.toString())
        val countriesArray = searchResponse.getJSONArray("countries")
        val countriesList = mutableListOf<League>()
        (0..<countriesArray.length()).forEach { i ->
            val countryObject = countriesArray.getJSONObject(i)
            val idLeague = countryObject.getString("idLeague")
            val strSport = countryObject.getString("strSport")
            val strLeague = countryObject.getString("strLeague")
            val strLeagueAlternate = countryObject.getString("strLeagueAlternate")

            val league = League(
                strLeague = strLeague,
                idLeague = idLeague.toInt(),
                strLeagueAlternate = strLeagueAlternate,
                strSport = strSport
            )
            countriesList.add(league)
        }
        println(countriesList)
        return countriesList
    }
}