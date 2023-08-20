package com.broadcaster.api.controller

import com.broadcaster.api.repository.broadcast.impl.BroadcastRepositoryImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val broadcastRepositoryImpl: BroadcastRepositoryImpl
) {

    @GetMapping
    fun get():String{
//        this.broadcastRepositoryImpl.get()
        return "Success"
    }
}