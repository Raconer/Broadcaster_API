package com.broadcaster.api.dto

import com.broadcaster.api.dto.sign.SignInDTO
import com.broadcaster.api.dto.sign.SignUpDTO
import net.datafaker.Faker

class SignIn {
    companion object{
        private val EMAIL = "christopher1.kling@hotmail.com"
        private val TEMP_PASSWORD = "1q2w3e4r"
        fun getData(): SignInDTO {
            return SignInDTO(
                this.EMAIL,
                this.TEMP_PASSWORD,
            )
        }
    }
}