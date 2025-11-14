package com.mercadolivro.model

import com.mercadolivro.enums.StatusEnum
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
    @Column
    @Enumerated(EnumType.STRING)
    var status: StatusEnum? = null,
    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null
)
