package me.shinyook.reactorguide

import reactor.core.publisher.Flux
import java.time.Duration

class Part01Flux {

    fun emptyFlux() = Flux.empty<String>()

    fun fooBarFluxFromValues() = Flux.just("foo", "bar")

    fun fooBarFluxFromList() = Flux.fromIterable(listOf("foo", "bar"))

    fun errorFlux() = Flux.error<String>(IllegalStateException())

    fun counter() = Flux.interval(Duration.ofMillis(100)).take(5)

}