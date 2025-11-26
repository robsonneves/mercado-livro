package com.mercadolivro.events.listener

import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.service.BookService
import com.mercadolivro.service.BookServiceImpl
import com.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UpdateSolBookListener(
    private  val bookServiceImpl: BookServiceImpl
) {

    @EventListener
    fun listen(purchaseEvent: PurchaseEvent){
        bookServiceImpl.purchase(purchaseEvent.purchaseModel.books)
    }
}