package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

internal class Part03StepVerifierTest {
    private val guideLine = Part03StepVerifier()

    @Test
    fun expectFooBarComplete() {
        guideLine.expectFooBarComplete(Flux.just("foo", "bar"))
    }

    @Test
    fun expectFooBarError() {
        guideLine.expectFooBarError(Flux.just("foo", "bar").concatWith(Mono.error(RuntimeException())))
    }

    @Test
    fun expectSkylerJesseComplete() {
        guideLine.expectSkylerJesseComplete(Flux.just(User.SKYLER, User.JESSE))
    }

    @Test
    fun expect10Elements() {
        guideLine.expect10Elements(Flux.range(1, 10))
    }

    @Test
    fun expect3600Elements() {
        guideLine.expect3600Elements { Flux.interval(Duration.ofSeconds(1)).take(3600) }
    }

}