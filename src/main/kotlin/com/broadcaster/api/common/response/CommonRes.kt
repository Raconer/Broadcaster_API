package com.broadcaster.api.common.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class CommonRes {
    companion object{
        fun Basic(httpStatus: HttpStatus?): ResponseEntity<Any> {
            return ResponseEntity.status(httpStatus!!).build()
        }

        fun Def(result: Any?): ResponseEntity<out Any> {
            return ResponseEntity.ok(ResponseMsg(result = result))
        }

        fun Except(httpStatus: HttpStatus?, result: Any?): ResponseEntity<Any> {
            return ResponseEntity.status(httpStatus!!).body(result)
        }
    }

}