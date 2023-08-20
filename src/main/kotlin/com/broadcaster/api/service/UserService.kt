package com.broadcaster.api.service

import com.broadcaster.api.dto.sign.SignDTO
import com.broadcaster.api.entity.users.Users
import com.broadcaster.api.repository.users.UsersRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val usersRepository: UsersRepository,
) : UserDetailsService {
    @Transactional(readOnly = true)
    override fun loadUserByUsername(email: String): SignDTO {
        val users: Users = this.getByEmail(email)
        return SignDTO(users.email!!)
    }

    @Transactional(readOnly = true)
    fun getByEmail(email: String): Users {
        return this.usersRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("Not Found Users")
    }
}