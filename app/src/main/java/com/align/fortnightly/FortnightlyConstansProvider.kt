package com.align.fortnightly

import com.align.domain.ConstantsProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FortnightlyConstansProvider @Inject constructor() : ConstantsProvider {
    override val baseUrl: String = BuildConfig.BaseUrl
    override val apiKey: String = BuildConfig.ApiKey
}