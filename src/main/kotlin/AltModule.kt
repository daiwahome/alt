package com.github.daiwahome.alt

import com.github.daiwahome.alt.cli.HelloCli
import com.github.daiwahome.alt.service.HelloService
import org.koin.dsl.module

val altModule = module {
    single { HelloCli(get()) }
    single { HelloService() }
}
