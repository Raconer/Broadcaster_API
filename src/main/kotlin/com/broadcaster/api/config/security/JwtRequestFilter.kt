package com.broadcaster.api.config.security

import com.broadcaster.api.dto.sign.SignDTO
import com.broadcaster.api.service.UserService
import com.broadcaster.api.utils.JwtUtil
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtRequestFilter(
    private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {

    private val EXCLUDE_URL = arrayListOf("/sign/up", "sign/in")
    private val TOKEN_PREFIX: String = "Bearer "
    private val SUB_LEN: Int = this.TOKEN_PREFIX.length
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val bearerToken = request.getHeader("Authorization")

        if (bearerToken != null && bearerToken.isNotEmpty() && bearerToken.startsWith(this.TOKEN_PREFIX)) {
            val token: String = bearerToken.substring(this.SUB_LEN)

            val claims = this.jwtUtil.getData(token)
            val email: String = claims["email"] as String
            val signDTO = SignDTO(email = email)
            val authenticationToken = UsernamePasswordAuthenticationToken(signDTO, null, signDTO.authorities)
            SecurityContextHolder.getContext().authentication = authenticationToken
        }

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return this.EXCLUDE_URL.stream().findFirst().filter {
            it.equals(request.requestURI)
        }.isPresent
    }
}