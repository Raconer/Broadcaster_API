package com.broadcaster.api.controller

import com.broadcaster.api.common.response.CommonRes
import com.broadcaster.api.dto.sign.SignDTO
import com.broadcaster.api.entity.broadcast.Broadcast
import com.broadcaster.api.service.BroadcastService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/broadcast")
class BroadcastController (
    private val broadcastService: BroadcastService
){

    @PostMapping
    fun insert(@AuthenticationPrincipal signDTO: SignDTO): ResponseEntity<out Any>{
        this.broadcastService.insert(signDTO)
        return CommonRes.Basic(HttpStatus.OK)
    }

    @GetMapping
    fun getList(): ResponseEntity<out Any>{
        val  broadcastList:List<Broadcast> = this.broadcastService.getList()
        return CommonRes.Def(broadcastList)
    }
}