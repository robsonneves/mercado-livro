package com.mercadolivro.service

import com.mercadolivro.enums.BookStatusEnum
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(
    private val bookRepository: BookRepository
) : GenericCrudService<BookModel, Int>{

    override fun repo() = bookRepository

    fun findAll(pageable: Pageable, name: String?): Page<BookModel> {

        name?.let { return bookRepository.findByNameContaining(pageable, name) }
        return bookRepository.findAll(pageable)
    }

    fun findByStatus(pageable: Pageable, active: BookStatusEnum): Page<BookModel> {
        return bookRepository.findByStatus(pageable, active)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for(book in books){
            book.status = BookStatusEnum.DELETADO
        }
        bookRepository.saveAll(books)
    }
}