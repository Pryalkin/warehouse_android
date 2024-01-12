package com.bsuir.kovko.sources.backend

import com.bsuir.kovko.sources.model.home.HomeSource
import com.bsuir.kovko.sources.model.auth.AuthSource

interface SourcesProvider {

    fun getAuthSource(): AuthSource
    fun getApiSource(): HomeSource

}