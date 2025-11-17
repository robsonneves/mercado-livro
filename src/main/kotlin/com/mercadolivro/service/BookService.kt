package com.mercadolivro.service

import com.mercadolivro.model.BookModel

interface BookService{

    fun findAll(name: String?): List<BookModel>
    fun save(toBookModel: BookModel)
}