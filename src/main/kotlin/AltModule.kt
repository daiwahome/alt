package com.github.daiwahome.alt

import com.github.daiwahome.alt.cli.AltCli
import com.github.daiwahome.alt.service.FilterService
import com.github.daiwahome.alt.service.GoogleApiService
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import org.koin.dsl.module

@kotlinx.serialization.UnstableDefault
val altModule = module {
    // CLI
    single { AltCli(get(), get()) }

    // Service
    single { FilterService() }
    single { GoogleApiService(get()) }

    // Other
    single {
        HttpClient(Apache) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }
}
