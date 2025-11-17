package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.enums.CustomerStatusEnum
import com.mercadolivro.enums.BookStatusEnum
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel{
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatusEnum.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(customePrevius: CustomerModel): CustomerModel{
    return CustomerModel(id = customePrevius.id, name = this.name, email = this.email, status = customePrevius.status)
}

fun PostBookRequest.toBookModel(customerModel: CustomerModel): BookModel{
    return BookModel(name = this.name, price = this.price, status = BookStatusEnum.ATIVO, customer = customerModel)
}

fun PutBookRequest.toBookModel(bookPrevious: BookModel): BookModel{
    return BookModel(
        id = bookPrevious.id,
        name = this.name ?: bookPrevious.name,
        price = this.price ?: bookPrevious.price,
        status = bookPrevious.status,
        customer = bookPrevious.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse{
    return  CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}
