package com.broadcaster.api.dto

import com.broadcaster.api.dto.sign.SignInDTO

class SignIn {
    companion object{
        val EMAIL = "test1@email.com"
        private val TEMP_PASSWORD = "1q2w3e4r"
        fun getData(): SignInDTO {
            return SignInDTO(
                this.EMAIL,
                this.TEMP_PASSWORD,
            )
        }
    }
}