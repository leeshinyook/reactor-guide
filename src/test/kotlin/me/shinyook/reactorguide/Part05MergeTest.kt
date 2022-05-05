package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import me.shinyook.reactorguide.repository.UserRepository
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

internal class Part05MergeTest {

    private val guideLine = Part05Merge()
    private val userRepository = UserRepository()

    @Test
    fun mergeFluxWithInterleave() {
        val flux = guideLine.mergeFluxWithInterleave(userRepository.findAllWithDelay(500), userRepository.findAll())
        StepVerifier.create(flux)
            .expectNext(User.JESSE, User.SKYLER, User.SAUL, User.WALTER, User.MIKE, User.MARIE)
            .verifyComplete()
    }

    @Test
    fun mergeFluxWithNoInterleave() {
        val flux = guideLine.mergeFluxWithNoInterleave(userRepository.findAllWithDelay(500), userRepository.findAll())
        StepVerifier.create(flux)
            .expectNext(User.MIKE, User.MARIE, User.JESSE, User.SKYLER, User.SAUL, User.WALTER)
            .verifyComplete()
    }

    @Test
    fun createFluxFromMultipleMono() {
        val mikeMono = userRepository.findFirstWithDelay(300)
        val jesseMono = userRepository.findFirst()
        val flux = guideLine.createFluxFromMultipleMono(mikeMono, jesseMono)
        StepVerifier.create(flux)
            .expectNext(User.MIKE, User.JESSE)
            .verifyComplete()
    }
}