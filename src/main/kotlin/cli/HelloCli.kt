package com.github.daiwahome.alt.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.output.TermUi
import com.github.daiwahome.alt.service.HelloService

class HelloCli(
    private val helloService: HelloService
) : CliktCommand(help = "hello world") {

    override fun run() = TermUi.echo(helloService.hello())
}
