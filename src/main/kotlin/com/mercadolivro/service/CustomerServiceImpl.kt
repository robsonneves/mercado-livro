package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl : CustomerService {

    val customers = mutableListOf<CustomerModel>()

    override fun getAll(name: String?): List<CustomerModel>{
        name?.let { return customers.filter { it.name.contains(name, true) } }
        return customers
    }

    override fun create(customer: PostCustomerRequest){
        val id = if(customers.isEmpty()) { 1 } else { customers.last().id.toInt() + 1 }.toString()
        customers.add(CustomerModel(id, customer.name, customer.email))
    }

    override fun getCustomer(id: String): CustomerModel{
        return customers.first { it.id == id }
    }

    override fun update(id: String, customer: PutCustomerRequest){
        customers.find{ it.id == id }?.let{
            it.name = customer.name
            it.email = customer.email
        }
    }

    override fun delete(id: String){
        customers.removeIf{ it.id == id }
    }
}