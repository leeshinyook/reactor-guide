package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import me.shinyook.reactorguide.repository.UserRepository
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

internal class Part04TransformTest {

    private val guideLine = Part04Transform()
    private val userRepository = UserRepository()

    @Test
    fun transformMono() {
        val user = userRepository.findFirst()
        StepVerifier.create(guideLine.capitalizeOne(user))
            .expectNext(User.JESSE.toUppercase())
            .verifyComplete()
    }

    @Test
    fun transformFlux() {
        val users = userRepository.findAll()
        StepVerifier.create(guideLine.capitalizeMany(users))
            .expectNext(User.JESSE.toUppercase(),
                User.SKYLER.toUppercase(),
                User.SAUL.toUppercase(),
                User.WALTER.toUppercase()
            )
            .verifyComplete()
    }

    @Test
    fun asyncTransformFlux() {
        val users = userRepository.findAll()
        StepVerifier.create(guideLine.asyncCapitalizeMany(users))
            .expectNext(User.JESSE.toUppercase(),
                User.SKYLER.toUppercase(),
                User.SAUL.toUppercase(),
                User.WALTER.toUppercase()
            )
            .verifyComplete()
    }
}