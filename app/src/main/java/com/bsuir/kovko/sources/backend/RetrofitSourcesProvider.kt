package com.bsuir.kovko.sources.backend

import com.bsuir.kovko.sources.model.home.HomeSource
import com.bsuir.kovko.sources.model.home.RetrofitApiSource
import com.bsuir.kovko.sources.model.auth.AuthSource
import com.bsuir.kovko.sources.model.auth.RetrofitAuthSource

class RetrofitSourcesProvider(
    private val config: RetrofitConfig
) : SourcesProvider {

    override fun getAuthSource(): AuthSource {
        return RetrofitAuthSource(config)
    }

    override fun getApiSource(): HomeSource {
        return RetrofitApiSource(config)
    }


}