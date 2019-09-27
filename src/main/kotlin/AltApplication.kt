package com.github.daiwahome.alt

import com.github.daiwahome.alt.cli.HelloCli
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class AltApplication : KoinComponent {

    val helloCli: HelloCli by inject()
}

fun main(args: Array<String>) {
    startKoin {
        modules(altModule)
    }

    val application = AltApplication()
    application.helloCli.main(args)
}
