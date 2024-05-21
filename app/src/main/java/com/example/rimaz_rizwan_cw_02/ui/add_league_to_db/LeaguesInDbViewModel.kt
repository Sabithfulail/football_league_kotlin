package com.example.rimaz_rizwan_cw_02.ui.add_league_to_db

import androidx.lifecycle.ViewModel
import com.example.rimaz_rizwan_cw_02.data.entity.League
import com.example.rimaz_rizwan_cw_02.data.repository.OfflineLeagueRepository
import com.example.rimaz_rizwan_cw_02.data.entity.Club
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class LeaguesInDbViewModel(private val repository: OfflineLeagueRepository) : ViewModel() {
    fun getAllLeagues(): Flow<List<League>> = repository.getAllLeagueStream()

    suspend fun saveListOfClubs(clubs:List<Club>){
        repository.insertListOfClub(clubs)
    }

    private val clubList = mutableListOf<Club>()

    private val _clubListStateFlow = MutableStateFlow<List<Club>>(emptyList())
    val clubListStateFlow: StateFlow<List<Club>> = _clubListStateFlow
    suspend fun fetchClubs(keyword: String): List<Club> {
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
        val clubs = parseJSON(stb)
        clubList.addAll(clubs)
        _clubListStateFlow.value = clubList
        return clubs
    }

    fun parseJSON(stb: StringBuilder): List<Club> {
        val searchResponse = JSONObject(stb.toString())
        val countriesArray = searchResponse.getJSONArray("countries")
        val clubList = mutableListOf<Club>()
        (0..<countriesArray.length()).forEach { i ->
            val countryObject = countriesArray.getJSONObject(i)
            val idLeague = countryObject.getString("idLeague")
            val strSport = countryObject.getString("strSport")
            val strLeague = countryObject.getString("strLeague")
            val strCurrentSeason = countryObject.getString("strCurrentSeason")
            val dateFirstEvent = countryObject.getString("dateFirstEvent")
            val strLeagueAlternate = countryObject.getString("strLeagueAlternate")
            val strGender = countryObject.getString("strGender")
            val strCountry = countryObject.getString("strCountry")
            val intDivision = countryObject.getString("intDivision")

            val club = Club(
                strLeague = strLeague,
                idLeague = idLeague,
                strLeagueAlternate = strLeagueAlternate,
                strSport = strSport,
                strCurrentSeason = strCurrentSeason,
                dateFirstEvent = dateFirstEvent,
                strGender = strGender,
                strCountry = strCountry,
                intDivision = intDivision
            )
            clubList.add(club)
        }
        println(clubList)
        return clubList
    }

    fun insertSearchClubsByLeague(listOfClub: Club){
//        repository.
    }
}