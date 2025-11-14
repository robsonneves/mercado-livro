package com.mercadolivro.service

import com.mercadolivro.model.BookModel
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

interface BookService{

    fun findAll(name: String?): List<BookModel>
    fun save(toBookModel: BookModel)
}