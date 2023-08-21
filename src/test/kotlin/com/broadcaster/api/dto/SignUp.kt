package com.broadcaster.api.dto

import com.broadcaster.api.dto.sign.SignUpDTO
import net.datafaker.Faker

class SignUp {
    companion object{
        private val DUPLICATE_EMAIL = "test1@email.com"
        private val TEMP_PASSWORD = "1q2w3e4r"
        private val faker = Faker()


        fun getData(): SignUpDTO {
            return SignUpDTO(
                faker.internet().emailAddress(),
                TEMP_PASSWORD,
                faker.name().fullName()
            )
        }

        fun getValidData(): SignUpDTO {
            return SignUpDTO(
                "",
                "",
                "")
        }

        fun getDuplicateData(): SignUpDTO {
            return SignUpDTO(
                DUPLICATE_EMAIL,
                TEMP_PASSWORD,
                faker.name().fullName()
            )
        }
    }
}