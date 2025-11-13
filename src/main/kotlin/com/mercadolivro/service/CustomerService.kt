package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel

interface CustomerService {

    fun getAll(name: String?): List<CustomerModel>
    fun create(customer: CustomerModel)
    fun getCustomer(id: String): CustomerModel
    fun update(customer: CustomerModel)
    fun delete(id: String)
}