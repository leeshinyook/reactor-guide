package me.shinyook.reactorguide.repository

import me.shinyook.reactorguide.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class UserRepository {

    fun findFirst(): Mono<User> = Mono.just(User.JESSE)

    fun findAll(): Flux<User> = Flux.just(User.JESSE, User.SKYLER, User.SAUL, User.WALTER)
}