package com.bsuir.kovko

import android.content.Context
import androidx.fragment.app.Fragment
import com.bsuir.kovko.app.repository.AuthRepository
import com.bsuir.kovko.app.repository.HomeRepository
import com.bsuir.kovko.app.screens.Navigator
import com.bsuir.kovko.app.setting.AppSettings
import com.bsuir.kovko.app.setting.SharedPreferencesAppSettings
import com.bsuir.kovko.sources.SourceProviderHolder
import com.bsuir.kovko.sources.backend.SourcesProvider
import com.bsuir.kovko.sources.model.home.HomeSource
import com.bsuir.kovko.sources.model.auth.AuthSource

object Singletons {

    private lateinit var appContext: Context

    private val sourcesProvider: SourcesProvider by lazy {
        SourceProviderHolder.sourcesProvider
    }

    val appSettings: AppSettings by lazy {
        SharedPreferencesAppSettings(appContext)
    }

    // source
    private val authSource: AuthSource by lazy {
        sourcesProvider.getAuthSource()
    }

    private val homeSource: HomeSource by lazy {
        sourcesProvider.getApiSource()
    }

    // repository
    val authRepository: AuthRepository by lazy {
        AuthRepository(
            authSource = authSource,
            appSettings = appSettings
        )
    }

    val apiRepository: HomeRepository by lazy {
        HomeRepository(
            homeSource = homeSource,
            appSettings = appSettings
        )
    }


    fun init(appContext: Context) {
        Singletons.appContext = appContext
    }

    fun Fragment.navigator() = requireActivity() as Navigator
}