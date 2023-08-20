package com.broadcaster.api.service

import com.broadcaster.api.dto.sign.SignInDTO
import com.broadcaster.api.dto.sign.SignUpDTO
import com.broadcaster.api.entity.users.Users
import com.broadcaster.api.repository.users.UsersRepository
import com.broadcaster.api.utils.JwtUtil
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class SignService(
    private val userService: UserService,
    private val usersRepository: UsersRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtUtil: JwtUtil
) {
    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = [Exception::class]
    )
    fun insert(signDTO: SignUpDTO) {
        val password = this.passwordEncoder.encode(signDTO.password)

        var users = Users(
            email = signDTO.email,
            password = password,
            name = signDTO.username
        )
        this.usersRepository.save(users)
    }

    @Transactional(readOnly = true)
    fun signIn(signInDTO: SignInDTO) : SignInDTO.Response{
        val email = signInDTO.email
        var signDTO = this.userService.loadUserByUsername(email)

        if (this.passwordEncoder.matches(signInDTO.password, signDTO.password)) {
            throw BadCredentialsException("Not Equals Sign In Data")
        }

        val token: String = this.jwtUtil.create(email)
        return SignInDTO.Response(
            email, token
        )
    }
}