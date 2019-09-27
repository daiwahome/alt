package com.github.daiwahome.alt

import com.github.daiwahome.alt.cli.HelloCli
import com.github.daiwahome.alt.service.FilterService
import com.github.daiwahome.alt.service.GoogleApiService
import com.github.daiwahome.alt.service.HelloService
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import org.koin.dsl.module

val altModule = module {
    // CLI
    single { HelloCli(get()) }

    // Service
    single { HelloService() }
    single { GoogleApiService(get()) }
    single { FilterService() }

    // Other
    single {
        HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }
}
