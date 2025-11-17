package com.mercadolivro.repository

import com.mercadolivro.enums.BookStatusEnum
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<BookModel, Int>{

    fun findByNameContaining(pageable: Pageable, name: String): Page<BookModel>
    fun findByStatus(pageable: Pageable, active: BookStatusEnum): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
}