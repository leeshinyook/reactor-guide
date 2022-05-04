package me.shinyook.reactorguide

import org.junit.jupiter.api.Test
import reactor.test.StepVerifier
import java.time.Duration

internal class Part02MonoTest {
    private val guideLine = Part02Mono()

    @Test
    fun empty() {
        val mono = guideLine.emptyMono()
        StepVerifier.create(mono)
            .verifyComplete()
    }

    @Test
    fun noSignal() {
        val mono = guideLine.monoWithNoSignal()
        StepVerifier.create(mono)
            .expectSubscription()
            .expectNoEvent(Duration.ofSeconds(1))
            .thenCancel()
            .verify()
    }

    @Test
    fun emitFoo() {
        val mono = guideLine.fooMono()
        StepVerifier.create(mono)
            .expectNext("foo")
            .verifyComplete()
    }

    @Test
    fun error() {
        val mono = guideLine.errorMono()
        StepVerifier.create(mono)
            .verifyError(IllegalStateException().javaClass)
    }
}