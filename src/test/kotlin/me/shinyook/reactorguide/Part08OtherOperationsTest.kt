package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import me.shinyook.reactorguide.repository.UserRepository
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

internal class Part08OtherOperationsTest {

    private val guideLine = Part08OtherOperations()
    private val userRepository = UserRepository()

    @Test
    fun zip() {
        val usernameFlux = Flux.just(User.SKYLER.username, User.MARIE.username, User.JESSE.username)
        val firstnameFlux = Flux.just(User.SKYLER.firstname, User.MARIE.firstname, User.JESSE.firstname)
        val lastnameFlux = Flux.just(User.SKYLER.lastname, User.MARIE.lastname, User.JESSE.lastname)
        val userFlux = guideLine.userFluxFromStringFlux(usernameFlux, firstnameFlux, lastnameFlux)
        StepVerifier.create(userFlux)
            .expectNext(User.SKYLER, User.MARIE, User.JESSE)
            .verifyComplete()
    }

    @Test
    fun fastestMono() {
        val fastUser = userRepository.findFirst()
        val slowUser = userRepository.findFirstWithDelay(500)
        StepVerifier.create(guideLine.userFastestMono(fastUser, slowUser))
            .expectNext(User.JESSE)
            .verifyComplete()
    }

    @Test
    fun fastestFlux() {
        val fastUsers = userRepository.findAll()
        val slowUsers = userRepository.findAllWithDelay(500)
        StepVerifier.create(guideLine.userFastestFlux(fastUsers, slowUsers))
            .expectNext(User.JESSE, User.SKYLER, User.SAUL, User.WALTER)
            .verifyComplete()
    }

    @Test
    fun complete() {
        val mono = guideLine.fluxCompletion(userRepository.findAll())
        StepVerifier.create(mono)
            .verifyComplete()
    }

    @Test
    fun nullHandling() {
        var mono = guideLine.nullAwareUserToMono(User.SKYLER)
        StepVerifier.create(mono)
            .expectNext(User.SKYLER)
            .verifyComplete()
        mono = guideLine.nullAwareUserToMono(null)
        StepVerifier.create(mono)
            .verifyComplete()
    }

    @Test
    fun emptyHandling() {
        var mono = guideLine.emptyToSkyler(Mono.just(User.SAUL))
        StepVerifier.create(mono)
            .expectNext(User.SAUL)
            .verifyComplete()
        mono = guideLine.emptyToSkyler(Mono.empty())
        StepVerifier.create(mono)
            .expectNext(User.SKYLER)
            .verifyComplete()
    }

    @Test
    fun fluxCollection() {
        val users = listOf<User>(User.SKYLER, User.JESSE, User.MARIE)
        val mono = guideLine.fluxCollection(Flux.fromIterable(users))
        StepVerifier.create(mono)
            .expectNext(users)
            .verifyComplete()
    }
}