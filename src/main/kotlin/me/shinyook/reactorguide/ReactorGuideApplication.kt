package me.shinyook.reactorguide

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactorGuideApplication

fun main(args: Array<String>) {
    runApplication<ReactorGuideApplication>(*args)
}
