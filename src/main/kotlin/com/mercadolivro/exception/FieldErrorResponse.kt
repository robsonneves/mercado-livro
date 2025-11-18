package com.mercadolivro.exception

data class FieldErrorResponse(
    var message: String,
    var field: String
)
