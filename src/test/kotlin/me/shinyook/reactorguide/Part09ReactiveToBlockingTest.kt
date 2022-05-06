package me.shinyook.reactorguide

import me.shinyook.reactorguide.domain.User
import me.shinyook.reactorguide.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Part09ReactiveToBlockingTest {

    private val guideLine = Part09ReactiveToBlocking()
    private val userRepository = UserRepository()

    @Test
    fun mono() {
        val mono = userRepository.findFirst()
        assertThat(guideLine.monoToValue(mono)).isEqualTo(User.JESSE)
    }

    @Test
    fun flux() {
        val flux = userRepository.findAll()
        val users = guideLine.fluxToValues(flux)
        val it = users.iterator()
        assertThat(it.next()).isEqualTo(User.JESSE)
        assertThat(it.next()).isEqualTo(User.SKYLER)
        assertThat(it.next()).isEqualTo(User.SAUL)
        assertThat(it.next()).isEqualTo(User.WALTER)
        assertThat(it.hasNext()).isFalse
    }
}