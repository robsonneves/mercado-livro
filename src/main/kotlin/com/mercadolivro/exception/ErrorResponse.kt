package com.mercadolivro.exception

data class ErrorResponse(
    var statusCode: Int,
    var message: String,
    var internalCode: String,
    var arrors: List<FieldErrorResponse>?
)
