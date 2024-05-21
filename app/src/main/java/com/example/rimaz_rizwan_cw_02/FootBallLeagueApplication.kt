
package com.example.rimaz_rizwan_cw_02

import android.app.Application
import com.example.rimaz_rizwan_cw_02.data.AppContainer
import com.example.rimaz_rizwan_cw_02.data.AppDataContainer

class FootBallLeagueApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
