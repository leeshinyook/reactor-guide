package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.time.Duration
import java.util.function.Supplier

/**
 * Initialize Flux.
 * Test on @test.kotlin.me.shinyook.reactorguide.Part03StepVerifier
 */
class Part03StepVerifier {

    fun expectFooBarComplete(flux: Flux<String>) =
        StepVerifier.create(flux)
            .expectNext("foo", "bar")
            .verifyComplete()


    fun expectFooBarError(flux: Flux<String>) =
        StepVerifier.create(flux)
            .expectNext("foo", "bar")
            .verifyError(RuntimeException().javaClass)

    fun expectSkylerJesseComplete(flux: Flux<User>) =
        StepVerifier.create(flux)
            .expectNextMatches { it.firstname == "Skyler" }
            .expectNextMatches { it.firstname == "Jesse" }
            .verifyComplete()

    fun expect10Elements(flux: Flux<Int>) =
        StepVerifier.create(flux)
            .expectNextCount(10)
            .verifyComplete()

    fun expect3600Elements(supplier: Supplier<Flux<Long>>) =
        StepVerifier.withVirtualTime(supplier)
            .thenAwait(Duration.ofSeconds(3600))
            .expectNextCount(3600)
            .verifyComplete()
}