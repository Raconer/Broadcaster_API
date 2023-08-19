package com.broadcaster.api.dto.sign

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class SignUpDTO(
    @field:NotEmpty @field:Email var email: String,
    @field:NotEmpty var password:String,
    @field:NotEmpty var username:String
)