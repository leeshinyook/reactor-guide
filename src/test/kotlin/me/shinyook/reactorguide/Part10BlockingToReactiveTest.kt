package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import me.shinyook.reactorguide.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

internal class Part10BlockingToReactiveTest {

    private val guideLine = Part10BlockingToReactive()
    private var userRepository: UserRepository = UserRepository()


    @Test
    fun slowPublisherFastSubscriber() {
        val flux = guideLine.blockingRepositoryToFlux(userRepository)
        assertThat(userRepository.callCount).isZero
        StepVerifier.create(flux)
            .expectNext(User.JESSE, User.SKYLER, User.SAUL, User.WALTER)
            .verifyComplete()
        assertThat(userRepository.callCount).isOne
    }

    @Test
    fun fastPublisherSlowSubscriber() {
        val complete: Mono<Void> = guideLine.fluxToBlockingRepository(userRepository)
        assertThat(userRepository.callCount).isEqualTo(1)
        StepVerifier.create(complete)
            .verifyComplete()
        assertThat(userRepository.callCount).isEqualTo(5)
    }
}