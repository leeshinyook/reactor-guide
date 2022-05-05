package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class Part05Merge {

    fun mergeFluxWithInterleave(flux1: Flux<User>, flux2: Flux<User>): Flux<User> = Flux.merge(flux1, flux2)

    fun mergeFluxWithNoInterleave(flux1: Flux<User>, flux2: Flux<User>): Flux<User> = Flux.mergeSequential(flux1, flux2)

    fun createFluxFromMultipleMono(mono1: Mono<User>, mono2: Mono<User>): Flux<User> = Flux.concat(mono1, mono2)
}