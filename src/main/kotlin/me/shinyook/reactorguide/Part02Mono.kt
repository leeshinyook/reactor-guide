package me.shinyook.reactorguide

import reactor.core.publisher.Mono

class Part02Mono {

    fun emptyMono() = Mono.empty<String>()

    fun monoWithNoSignal() = Mono.never<String>()

    fun fooMono() = Mono.just("foo")

    fun errorMono() = Mono.error<String>(IllegalStateException())
}