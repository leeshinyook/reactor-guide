package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

internal class Part07ErrorTest {

    private val guideLine = Part07Error()

    @Test
    fun monoWithValueInsteadOfError() {
        var mono = guideLine.betterCallSaulForBogusMono(Mono.error(IllegalStateException()))
        StepVerifier.create(mono)
            .expectNext(User.SAUL)
            .verifyComplete()
        mono = guideLine.betterCallSaulForBogusMono(Mono.just(User.JESSE))
        StepVerifier.create(mono)
            .expectNext(User.JESSE)
            .verifyComplete()
    }

    @Test
    fun fluxWithValueInsteadOfError() {
        var flux = guideLine.betterCallSaulAndJesseForBogusFlux(Flux.error(IllegalStateException()))
        StepVerifier.create(flux)
            .expectNext(User.SAUL, User.JESSE)
            .verifyComplete()
        flux = guideLine.betterCallSaulAndJesseForBogusFlux(Flux.just(User.MIKE, User.MARIE))
        StepVerifier.create(flux)
            .expectNext(User.MIKE, User.MARIE)
            .verifyComplete()
    }

    @Test
    fun handleCheckedExceptions() {
        val flux = guideLine.capitalizeMany(Flux.just(User.MARIE, User.SAUL))
        StepVerifier.create(flux, 0)
            .thenRequest(1)
            .expectNext(User.MARIE)
            .thenRequest(1)
            .verifyError(Part07Error.GetOutOfHereException().javaClass)
    }
}