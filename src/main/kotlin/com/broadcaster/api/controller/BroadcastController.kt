package com.broadcaster.api.controller

import com.broadcaster.api.common.response.CommonRes
import com.broadcaster.api.dto.PageDTO
import com.broadcaster.api.dto.broadcast.BroadcastUpdateDTO
import com.broadcaster.api.dto.broadcast.BroadcastDataDTO
import com.broadcaster.api.dto.sign.SignDTO
import com.broadcaster.api.service.BroadcastService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    fun getList(@ModelAttribute pageDTO: PageDTO): ResponseEntity<out Any>{
        println(pageDTO)
        val broadcastList:List<BroadcastDataDTO> = this.broadcastService.getList(pageDTO)
        return CommonRes.Def(broadcastList)
    }

    @PutMapping
    fun update(@RequestBody @Valid broadcastUpdateDTO: BroadcastUpdateDTO,
               @AuthenticationPrincipal signDTO:SignDTO): ResponseEntity<out Any>{
        this.broadcastService.update(broadcastUpdateDTO, signDTO.username)
        return CommonRes.Basic(HttpStatus.OK)
    }
}