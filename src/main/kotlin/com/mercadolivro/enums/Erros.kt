package com.mercadolivro.enums

enum class Erros(val code: String, val message: String) {

    ML0001("ML-0001", "Customer [%s] not exists"),
    ML0002("ML-0002", "Cannot update book with status [%s]"),
    ML0003("ML-0003", "Invalid Request")
}