package com.example.rimaz_rizwan_cw_02.ui.search_club_by_league

import androidx.lifecycle.ViewModel
import com.example.rimaz_rizwan_cw_02.data.entity.Club
import com.example.rimaz_rizwan_cw_02.data.repository.OfflineLeagueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class SearchClubByLgModel(private val repository: OfflineLeagueRepository) : ViewModel() {
    private val clubList = mutableListOf<Club>()

    private val _clubListStateFlow = MutableStateFlow<List<Club>>(emptyList())
    val clubListStateFlow: StateFlow<List<Club>> = _clubListStateFlow
    suspend fun fetchClubs(keyword: String): List<Club> {
        val urlString =
            "https://www.thesportsdb.com/api/v1/json/3/search_all_leagues.php?c=$keyword&s=Soccer"
        println(urlString)
        val url = URL(urlString)
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

    private fun parseJSON(stb: StringBuilder): List<Club> {
        val searchResponse = JSONObject(stb.toString())
        val countriesArray = searchResponse.getJSONArray("countries")
        val clubList = mutableListOf<Club>()
        (0..<countriesArray.length()).forEach { i ->
            val countryObject = countriesArray.getJSONObject(i)
            val idLeague = countryObject?.getString("idLeague") ?: ""
            val strSport = countryObject?.getString("strSport") ?: ""
            val strLeague = countryObject?.getString("strLeague") ?: ""
            val strCurrentSeason = countryObject?.getString("strCurrentSeason") ?: ""
            val dateFirstEvent = countryObject?.getString("dateFirstEvent") ?: ""
            val strLeagueAlternate = countryObject?.getString("strLeagueAlternate") ?: ""
            val strGender = countryObject?.getString("strGender") ?: ""
            val strCountry = countryObject?.getString("strCountry") ?: ""
            val intDivision = countryObject?.getString("intDivision") ?: ""

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

    suspend fun saveListOfClubs(clubs: List<Club>) {
        repository.insertListOfClub(clubs)
    }
}