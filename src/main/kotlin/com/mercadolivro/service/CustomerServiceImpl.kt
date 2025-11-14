package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(
    val customerRepository: CustomerRepository
) : CustomerService {

    override fun getAll(name: String?): List<CustomerModel>{

        name?.let { return customerRepository.findByNameContaining(name) }
        return customerRepository.findAll().toList()
    }

    override fun create(customer: CustomerModel){
        customerRepository.save(customer)
    }

    override fun getById(id: Int): CustomerModel{
        return customerRepository.findById(id).orElseThrow()
    }

    override fun update(customer: CustomerModel){

        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }
        customerRepository.save(customer)
    }

    override fun delete(id: Int){

        if(!customerRepository.existsById(id)){
            throw Exception()
        }
        customerRepository.deleteById(id)
    }
}