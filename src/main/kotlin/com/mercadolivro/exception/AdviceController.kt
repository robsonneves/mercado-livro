package com.mercadolivro.exception

import com.mercadolivro.enums.Erros
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class AdviceController {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {

        val erro = ErrorResponse(
            statusCode = HttpStatus.NOT_FOUND.value(),
            message = ex.message,
            internalCode = ex.errorCode,
            arrors = null
        )
        return ResponseEntity(erro, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {

        val erro = ErrorResponse(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            message = ex.message,
            internalCode = ex.errorCode,
            arrors = null
        )
        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {

        val erro = ErrorResponse(
            statusCode = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            message = Erros.ML0003.message,
            internalCode = Erros.ML0003.code,
            arrors = ex.bindingResult.fieldErrors.map { FieldErrorResponse( it.defaultMessage ?: "invalid", it.field )}
        )
        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }
}