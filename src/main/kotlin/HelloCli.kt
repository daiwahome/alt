package com.github.daiwahome.alt

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.output.TermUi

class HelloCli : CliktCommand(help = "hello world") {

    override fun run() = TermUi.echo("hello world")
}
