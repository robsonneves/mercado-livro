package com.mercadolivro.repository

import com.mercadolivro.enums.BookStatusEnum
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel, Int>{

    fun findByNameContaining(name: String): List<BookModel>
    fun findByStatus(active: BookStatusEnum): List<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
}