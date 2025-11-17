package com.mercadolivro.service

import com.mercadolivro.enums.BookStatusEnum
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
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

    fun findByStatus(active: BookStatusEnum): List<BookModel> {
        return bookRepository.findByStatus(active)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for(book in books){
            book.status = BookStatusEnum.DELETADO
        }
        bookRepository.saveAll(books)
    }
}