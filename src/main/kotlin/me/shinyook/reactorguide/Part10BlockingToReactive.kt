package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import me.shinyook.reactorguide.repository.UserRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

class Part10BlockingToReactive {

    fun blockingRepositoryToFlux(userRepository: UserRepository): Flux<User> =
        Flux.defer { userRepository.findAll() }.subscribeOn(Schedulers.boundedElastic())

    fun fluxToBlockingRepository(userRepository: UserRepository): Mono<Void> =
        userRepository.findAll().publishOn(Schedulers.boundedElastic()).doOnNext { userRepository.save(it) }.then()

}