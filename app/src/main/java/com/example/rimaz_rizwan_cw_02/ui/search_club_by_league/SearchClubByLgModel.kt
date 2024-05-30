package com.example.rimaz_rizwan_cw_02.ui.search_club_by_league

import androidx.lifecycle.ViewModel
import com.example.rimaz_rizwan_cw_02.data.entity.Club
import com.example.rimaz_rizwan_cw_02.data.repository.OfflineLeagueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SearchClubByLgModel(private val repository: OfflineLeagueRepository) : ViewModel() {
    private val clubList = mutableListOf<Club>()
    private val _clubListStateFlow = MutableStateFlow<List<Club>>(emptyList())
    val clubListStateFlow: StateFlow<List<Club>> = _clubListStateFlow
    fun clearClubs() {
        _clubListStateFlow.value = emptyList()
    }
    suspend fun fetchClubs(keyword: String) {
        val urlString =
            "https://www.thesportsdb.com/api/v1/json/3/search_all_leagues.php?c=$keyword&s=Soccer"
        val url = URL(urlString)
        println(url)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection

        val stb = StringBuilder()

        withContext(Dispatchers.IO) {
            try {
                val bf = BufferedReader(InputStreamReader(con.inputStream))
                var line: String? = bf.readLine()
                while (line != null) {
                    stb.append(line + "\n")
                    line = bf.readLine()
                }

                val clubs = parseJSON(stb)
                if (clubs.isEmpty()) {
                    throw JSONException("No clubs found")
                }
                clubList.addAll(clubs)
                _clubListStateFlow.value = clubList
            } catch (e: IOException) {
                throw IOException("Error reading from input stream: ${e.message}")
            } catch (e: JSONException) {
                throw JSONException("Error parsing JSON response: ${e.message}")
            } finally {
                con.disconnect()
            }
        }
    }

    private fun parseJSON(stb: StringBuilder): List<Club> {
        val searchResponse = JSONObject(stb.toString())
        if (!searchResponse.has("countries") || searchResponse.isNull("countries")) {
            throw JSONException("No 'countries' key found or it is null")
        }

        val countriesArray = searchResponse.getJSONArray("countries")
        val clubList = mutableListOf<Club>()
        for (i in 0 until countriesArray.length()) {
            val countryObject = countriesArray.getJSONObject(i)
            val idLeague = countryObject.optString("idLeague", "")
            val strSport = countryObject.optString("strSport", "")
            val strLeague = countryObject.optString("strLeague", "")
            val strCurrentSeason = countryObject.optString("strCurrentSeason", "")
            val dateFirstEvent = countryObject.optString("dateFirstEvent", "")
            val strLeagueAlternate = countryObject.optString("strLeagueAlternate", "")
            val strGender = countryObject.optString("strGender", "")
            val strCountry = countryObject.optString("strCountry", "")
            val intDivision = countryObject.optString("intDivision", "")
            val strBadge = countryObject.optString("strBadge", "")

            val club = Club(
                strLeague = strLeague,
                idLeague = idLeague,
                strLeagueAlternate = strLeagueAlternate,
                strSport = strSport,
                strCurrentSeason = strCurrentSeason,
                dateFirstEvent = dateFirstEvent,
                strGender = strGender,
                strCountry = strCountry,
                intDivision = intDivision,
                strBadge = strBadge
            )
            clubList.add(club)
        }
        return clubList
    }

    suspend fun saveListOfClubs(clubs: List<Club>) {
        repository.insertListOfClub(clubs)
    }
}
