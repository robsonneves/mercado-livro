package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl : CustomerService {

    val customers = mutableListOf<CustomerModel>()

    override fun getAll(name: String?): List<CustomerModel>{
        name?.let { return customers.filter { it.name.contains(name, true) } }
        return customers
    }

    override fun create(customer: CustomerModel){
        val id = if(customers.isEmpty()) { 1 } else { customers.last().id!!.toInt() + 1 }.toString()
        customer.id = id
        customers.add(customer)
    }

    override fun getCustomer(id: String): CustomerModel{
        return customers.first { it.id.equals(id) }
    }

    override fun update(customer: CustomerModel){
        customers.find{ it.id.equals(customer.id) }?.let{
            it.name = customer.name
            it.email = customer.email
        }
    }

    override fun delete(id: String){
        customers.removeIf{ it.id.equals(id) }
    }
}