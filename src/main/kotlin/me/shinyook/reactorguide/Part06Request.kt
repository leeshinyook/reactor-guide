package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import me.shinyook.reactorguide.repository.UserRepository
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class Part06Request {

    private val userRepository = UserRepository()

    fun requestAllExpectFour(flux: Flux<User>): StepVerifier =
        StepVerifier.create(flux)
            .expectNextCount(4)
            .expectComplete()

    fun requestOneExpectSkylerThenRequestOneExpectJesse(flux: Flux<User>): StepVerifier =
        StepVerifier.create(flux)
            .expectNext(User.SKYLER)
            .thenRequest(1)
            .expectNext(User.JESSE)
            .thenCancel()

    fun fluxWithLog(): Flux<User> = userRepository.findAll().log()

    fun fluxWithDoOnPrintln(): Flux<User> = userRepository.findAll()
        .doOnSubscribe { println("Starring:") }
        .doOnNext { println("${it.firstname} ${it.lastname}") }
        .doOnComplete { println("The end!") }

}