package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel

interface CustomerService {

    fun getAll(name: String?): List<CustomerModel>
    fun create(customer: PostCustomerRequest)
    fun getCustomer(id: String): CustomerModel
    fun update(id: String, customer: PutCustomerRequest)
    fun delete(id: String)
}