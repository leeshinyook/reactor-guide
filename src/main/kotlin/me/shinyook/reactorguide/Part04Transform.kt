package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class Part04Transform {

    fun capitalizeOne(mono: Mono<User>): Mono<User> =
        mono.map { User(it.username.uppercase(), it.firstname.uppercase(), it.lastname.uppercase()) }

    fun capitalizeMany(flux: Flux<User>): Flux<User> =
        flux.map { User(it.username.uppercase(), it.firstname.uppercase(), it.lastname.uppercase()) }

    fun asyncCapitalizeMany(flux: Flux<User>): Flux<User> =
        flux.flatMap { Mono.just(User(it.username.uppercase(), it.firstname.uppercase(), it.lastname.uppercase())) }
}