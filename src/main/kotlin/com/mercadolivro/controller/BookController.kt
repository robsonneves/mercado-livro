package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.enums.StatusEnum
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.model.BookModel
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
    fun findAll(@RequestParam name: String?): List<BookModel>{
        return bookServiceImpl.findAll(name)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody book: PostBookRequest){

        val customer = getCustomer(book.customerId)
        bookServiceImpl.save(book.toBookModel(customer))
    }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id: Int): BookModel{
        return bookServiceImpl.findById(id)
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

        var book = bookServiceImpl.findById(id)
        book.status = StatusEnum.CANCELADO
        bookServiceImpl.save(book)
    }

    @GetMapping("/active")
    fun findActives(): List<BookModel>{
        return bookServiceImpl.findByStatus(StatusEnum.ATIVO)
    }

    private fun getCustomer(idCustomer: Int): CustomerModel {
        return customerService.getById(idCustomer)
    }
}