package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class Part09ReactiveToBlocking {

    fun monoToValue(mono: Mono<User>): User? = mono.block()

    fun fluxToValues(flux: Flux<User>): Iterable<User> = flux.toIterable()

}