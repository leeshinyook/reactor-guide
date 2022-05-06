package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class Part08OtherOperations {

    fun userFluxFromStringFlux(
        usernameFlux: Flux<String>,
        firstnameFlux: Flux<String>,
        lastnameFlux: Flux<String>,
    ): Flux<User> =
        Flux.zip(usernameFlux, firstnameFlux, lastnameFlux)
            .flatMap { Flux.just(User(it.t1, it.t2, it.t3)) }

    fun userFastestMono(mono1: Mono<User>, mono2: Mono<User>): Mono<User> =
        Mono.firstWithValue(mono1, mono2)

    fun userFastestFlux(flux1: Flux<User>, flux2: Flux<User>): Flux<User> =
        Flux.firstWithValue(flux1, flux2)

    fun fluxCompletion(flux: Flux<User>): Mono<Void> =
        flux.ignoreElements().then()

    fun nullAwareUserToMono(user: User?): Mono<User> =
        Mono.justOrEmpty(user)

    fun emptyToSkyler(mono: Mono<User>): Mono<User> =
        mono.defaultIfEmpty(User.SKYLER)

    fun fluxCollection(flux: Flux<User>): Mono<List<User>> =
        flux.collectList()
}