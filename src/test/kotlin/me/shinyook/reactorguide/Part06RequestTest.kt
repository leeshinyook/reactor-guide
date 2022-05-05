package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import me.shinyook.reactorguide.repository.UserRepository
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

internal class Part06RequestTest {

    private val guideLine = Part06Request()
    private val userRepository = UserRepository()

    @Test
    fun requestAll() {
        val users = userRepository.findAll()
        guideLine.requestAllExpectFour(users).verify()
    }

    @Test
    fun requestOneByOne() {
        val users = Flux.just(User.SKYLER, User.JESSE, User.MIKE)
        guideLine.requestOneExpectSkylerThenRequestOneExpectJesse(users).verify()
    }

    @Test
    fun fluxLog() {
        val flux = guideLine.fluxWithLog()
        StepVerifier.create(flux, 0)
            .thenRequest(1)
            .expectNextMatches { true }
            .thenRequest(1)
            .expectNextMatches { true }
            .thenRequest(2)
            .expectNextMatches { true }
            .expectNextMatches { true }
            .verifyComplete()
    }

    @Test
    fun fluxWithDoOn() {
        val flux = guideLine.fluxWithDoOnPrintln()
        StepVerifier.create(flux)
            .expectNextCount(4)
            .verifyComplete()
    }

}