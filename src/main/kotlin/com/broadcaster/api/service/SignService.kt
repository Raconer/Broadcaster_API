package com.broadcaster.api.service

import com.broadcaster.api.dto.sign.SignUpDTO
import com.broadcaster.api.entity.users.Users
import com.broadcaster.api.repository.users.UsersRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SignService(
    private val usersRepository: UsersRepository,
    private val passwordEncoder: BCryptPasswordEncoder
)  {

    fun insert(signDTO: SignUpDTO):Int? {
        val password = this.passwordEncoder.encode(signDTO.password)

        var users = Users(
            email = signDTO.email,
            password = password,
            name = signDTO.username
        )
        this.usersRepository.save(users)
        return users.id
    }
}