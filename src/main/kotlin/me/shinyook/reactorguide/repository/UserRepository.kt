package me.shinyook.reactorguide.repository

import me.shinyook.reactorguide.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

class UserRepository {

    var callCount = 0

    fun findFirst(): Mono<User> = Mono.just(User.JESSE)

    fun findAll(): Flux<User> {
        callCount++
        return Flux.just(User.JESSE, User.SKYLER, User.SAUL, User.WALTER)
    }

    fun findAllWithDelay(delay: Long): Flux<User> = Flux.just(User.MIKE, User.MARIE).delayElements(Duration.ofMillis(delay))

    fun findFirstWithDelay(delay: Long): Mono<User> = Mono.just(User.MIKE).delayElement(Duration.ofMillis(delay))

    fun save(user: User): Mono<User> {
        callCount++
        return Mono.just(user)
    }
}