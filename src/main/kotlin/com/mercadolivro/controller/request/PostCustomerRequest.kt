package com.mercadolivro.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotEmpty(message = "Nome Invalido!")
    val name: String,
    @field:Email(message = "E-mail deve ser valido!")
    val email: String
)
