package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.enums.BookStatusEnum
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.service.BookServiceImpl
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(
    val bookServiceImpl: BookServiceImpl,
    val customerService: CustomerService
) {

    @GetMapping
    fun findAll(@RequestParam name: String?): List<BookResponse>{
        return bookServiceImpl.findAll(name).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody book: PostBookRequest){

        val customer = getCustomer(book.customerId)
        bookServiceImpl.save(book.toBookModel(customer))
    }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id: Int): BookResponse{
        return bookServiceImpl.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest){

        val book = book.toBookModel(bookServiceImpl.findById(id))
        bookServiceImpl.save(book)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){

        val book = bookServiceImpl.findById(id)
        book.status = BookStatusEnum.CANCELADO
        bookServiceImpl.save(book)
    }

    @GetMapping("/active")
    fun findActives(): List<BookResponse>{
        return bookServiceImpl.findByStatus(BookStatusEnum.ATIVO).map { it.toResponse() }
    }

    private fun getCustomer(idCustomer: Int): CustomerModel {
        return customerService.findById(idCustomer)
    }
}