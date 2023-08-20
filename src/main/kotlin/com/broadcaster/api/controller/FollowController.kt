package com.broadcaster.api.controller

import com.broadcaster.api.common.response.CommonRes
import com.broadcaster.api.dto.follow.FollowDTO
import com.broadcaster.api.dto.sign.SignDTO
import com.broadcaster.api.service.FollowService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/follow")
class FollowController(
    private val followService: FollowService
) {
    @PostMapping
    fun insert(
        @RequestBody @Valid followDTO: FollowDTO,
        @AuthenticationPrincipal signDTO: SignDTO
    ): ResponseEntity<out Any> {
        this.followService.insert(followDTO, signDTO.username)
        return CommonRes.Basic(HttpStatus.OK)
    }
}