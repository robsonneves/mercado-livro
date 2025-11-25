package com.mercadolivro.controller.request

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class PostPurchaseRequest(

    @field:NotNull
    @field:Positive
    val costumerId: Int,

    @NotNull
    val bookIds: Set<Int>
)
