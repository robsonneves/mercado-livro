package com.mercadolivro.controller.request

import com.mercadolivro.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotEmpty(message = "Nome Invalido!")
    val name: String,
    @field:Email(message = "E-mail deve ser valido!")
    @EmailAvailable(message = "Email já existe")
    val email: String,
    @field:NotEmpty(message = "Senha deve ser informada")
    var password: String
)
