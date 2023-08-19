package com.broadcaster.api.dto.sign

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class SignInDTO(
    @field:NotEmpty @field:Email var email: String,
    @field:NotEmpty var password: String,
) {
    data class Response(
        var email:String,
        var token:String
    )
}
