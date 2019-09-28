package com.github.daiwahome.alt

import com.github.daiwahome.alt.cli.AltCli
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.inject

@kotlinx.serialization.UnstableDefault
class AltApplication : KoinComponent {

    val altCli: AltCli by inject()
}

@kotlinx.serialization.UnstableDefault
fun main(args: Array<String>) {
    startKoin {
        modules(altModule)
    }

    val application = AltApplication()
    application.altCli.main(args)

    stopKoin()
}
