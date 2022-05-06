package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class Part07Error {

    fun betterCallSaulForBogusMono(mono: Mono<User>) =
        mono.onErrorReturn(User.SAUL)

    fun betterCallSaulAndJesseForBogusFlux(flux: Flux<User>) =
        flux.onErrorResume { Flux.just(User.SAUL, User.JESSE) }

    fun capitalizeMany(flux: Flux<User>): Flux<User> =
        flux.map { capitalizeUser(it) }

    private fun capitalizeUser(user: User): User =
        if (user == User.SAUL) throw GetOutOfHereException()
        else User(user.username, user.firstname, user.lastname)

    class GetOutOfHereException : Exception()
}