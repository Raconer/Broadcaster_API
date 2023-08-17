package com.broadcaster.api.common.exception

import com.broadcaster.api.common.response.CommonRes
import com.broadcaster.api.common.response.ValidInfo
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.sql.SQLIntegrityConstraintViolationException
import java.util.stream.Collectors

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ControllerAdvice : ResponseEntityExceptionHandler() {
    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun handleSQLIntegrityConstraintViolationException(ex: SQLIntegrityConstraintViolationException): ResponseEntity<Any> {
        logger.error(
            "SQLIntegrityConstraintViolationException occurred: ${ex.message}"
        )
        return CommonRes.Except(HttpStatus.INTERNAL_SERVER_ERROR, ex.message)
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(ex: UsernameNotFoundException): ResponseEntity<Any> {
        error(
            "handleUsernameNotFoundException occurred: ${ex.message}"
        )
        return CommonRes.Except(HttpStatus.UNAUTHORIZED, ex.message)
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentialsException(ex: BadCredentialsException): ResponseEntity<Any> {
        return CommonRes.Except(HttpStatus.UNAUTHORIZED, ex.message)
    }

    @Override
    protected fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders?,
        status: HttpStatus?,
        request: WebRequest?
    ): ResponseEntity<Any> {
        val errorList: List<ValidInfo> = ex.bindingResult.fieldErrors.stream().map {
            ValidInfo(it.field, it.defaultMessage)
        }.collect(Collectors.toList())
        return CommonRes.Except(HttpStatus.BAD_REQUEST, errorList)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorList: List<ValidInfo> = ex.bindingResult.fieldErrors.stream().map {
            ValidInfo(it.field, it.defaultMessage)
        }.collect(Collectors.toList())
        return CommonRes.Except(HttpStatus.BAD_REQUEST, errorList)
    }

    override fun handleBindException(
        ex: BindException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorList: List<ValidInfo> = ex.bindingResult.fieldErrors.stream().map {
            ValidInfo(it.field, it.defaultMessage)
        }.collect(Collectors.toList())
        return CommonRes.Except(HttpStatus.BAD_REQUEST, errorList)
    }

}