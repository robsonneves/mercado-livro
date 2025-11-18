package com.mercadolivro.model

import com.mercadolivro.enums.BookStatusEnum
import com.mercadolivro.enums.Erros
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column
    var name: String,
    @Column
    var price: BigDecimal,
    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null
){

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatusEnum? = null
        set(value){
            if(field == BookStatusEnum.CANCELADO || field == BookStatusEnum.DELETADO){
                throw org.apache.coyote.BadRequestException(Erros.ML0002.message.format((field), Erros.ML0002.code))
            }
        }

    constructor(id: Int? = null,
                name: String,
                price: BigDecimal,
                customer: CustomerModel? = null,
                status: BookStatusEnum?): this(id, name, price, customer) {
        this.status = status
    }
}
