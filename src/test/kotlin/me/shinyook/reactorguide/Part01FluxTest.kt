package me.shinyook.reactorguide

import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

internal class Part01FluxTest {

    private val guideLine = Part01Flux()

    @Test
    fun empty() {
        val emptyFlux = guideLine.emptyFlux()
        StepVerifier.create(emptyFlux).verifyComplete()
    }

    @Test
    fun fromValues() {
        val flux = guideLine.fooBarFluxFromValues()
        StepVerifier.create(flux)
            .expectNext("foo", "bar")
            .verifyComplete()
    }

    @Test
    fun fromList() {
        val flux = guideLine.fooBarFluxFromList()
        StepVerifier.create(flux)
            .expectNext("foo", "bar")
            .verifyComplete()
    }

    @Test
    fun error() {
        val flux = guideLine.errorFlux()
        StepVerifier.create(flux)
            .verifyError(IllegalStateException().javaClass)
    }

    @Test
    fun counter() {
        val flux = guideLine.counter()
        StepVerifier.create(flux)
            .expectNext(0L, 1L, 2L, 3L, 4L)
            .verifyComplete()

    }
}