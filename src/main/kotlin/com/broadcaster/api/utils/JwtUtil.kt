package com.broadcaster.api.utils

import com.broadcaster.api.common.exception.CustomAuthenticationException
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${common.jwt.secret_key}")
    private val secretKey: String? = null

    @Value("\${common.jwt.expire_time}")
    private val expired: Long = 0

    fun create(email: String): String {
        val header: MutableMap<String, Any> = HashMap()
        val payloads: MutableMap<String, Any> = HashMap()
        header["type"] = "jwt"
        payloads["email"] = email
        val curDate = Date()
        val expiredDate = Date(curDate.time + expired)
        return Jwts.builder()
            .setHeader(header)
            .setClaims(payloads)
            .setIssuedAt(curDate)
            .setExpiration(expiredDate)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getData(token: String?): Claims {
        isValidate(token)
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body
    }

    fun isValidate(token: String?): Boolean? {
        if (token == null) throw CustomAuthenticationException("토큰이 Null 입니다.")
        return try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            true
        } catch (ex: AuthenticationException) {
            throw CustomAuthenticationException("인증에 실패하였습니다", ex)
        } catch (ex: SignatureException) {
            throw CustomAuthenticationException("잘못된 JWT 서명입니다", ex)
        } catch (ex: MalformedJwtException) {
            throw CustomAuthenticationException("잘못된 JWT 토큰입니다", ex)
        } catch (ex: ExpiredJwtException) {
            throw CustomAuthenticationException("토큰 유효기간이 만료되었습니다", ex)
        } catch (ex: UnsupportedJwtException) {
            throw CustomAuthenticationException("지원되지 않는 JWT 토큰입니다", ex)
        } catch (ex: IllegalArgumentException) {
            throw CustomAuthenticationException("JWT claims 문자열이 비어있습니다", ex)
        }
    }

}