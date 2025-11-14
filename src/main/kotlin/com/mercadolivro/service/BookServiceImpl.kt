package com.mercadolivro.service

import com.mercadolivro.enums.StatusEnum
import com.mercadolivro.model.BookModel
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(
    private val bookRepository: BookRepository
) : GenericCrudService<BookModel, Int>{

    override fun repo() = bookRepository

    fun findAll(name: String?): List<BookModel> {

        name?.let { return bookRepository.findByNameContaining(name) }
        return bookRepository.findAll().toList()
    }

    fun findByStatus(active: StatusEnum): List<BookModel> {
        return bookRepository.findByStatus(active)
    }
}