package com.broadcaster.api.controller

import com.broadcaster.api.common.response.CommonRes
import com.broadcaster.api.dto.sign.SignUpDTO
import com.broadcaster.api.service.SignService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/sign")
class SignController (
    private val signService: SignService
){
    @PostMapping("/up")
    fun signUp(@RequestBody @Valid signUpDTO: SignUpDTO): ResponseEntity<out Any>{
        val result:Int? = this.signService.insert(signUpDTO)
        println(result)
        return CommonRes.Basic(HttpStatus.OK)
    }
}