package com.broadcaster.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BroadcasterApiApplication

fun main(args: Array<String>) {
    runApplication<BroadcasterApiApplication>(*args)
}
