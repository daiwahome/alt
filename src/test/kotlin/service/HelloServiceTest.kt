package service

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.service.HelloService
import org.junit.jupiter.api.Test

internal class HelloServiceTest {

    private val sut = HelloService()

    @Test
    fun hello() {
        assertThat(sut.hello()).isEqualTo("hello world")
    }
}