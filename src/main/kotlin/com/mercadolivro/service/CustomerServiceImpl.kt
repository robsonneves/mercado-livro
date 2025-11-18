package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatusEnum
import com.mercadolivro.enums.Erros
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(
    val customerRepository: CustomerRepository,
    val bookServiceImpl: BookServiceImpl
) : CustomerService {

    override fun getAll(name: String?): List<CustomerModel>{

        name?.let { return customerRepository.findByNameContaining(name) }
        return customerRepository.findAll().toList()
    }

    override fun create(customer: CustomerModel){
        customerRepository.save(customer)
    }

    override fun findById(id: Int): CustomerModel{
        return customerRepository.findById(id).orElseThrow{ NotFoundException(Erros.ML0001.message.format(id), Erros.ML0001.code) }
    }

    override fun update(customer: CustomerModel){

        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }
        customerRepository.save(customer)
    }

    override fun delete(id: Int){
        var customer = findById(id);
        bookServiceImpl.deleteByCustomer(customer)
        customer.status = CustomerStatusEnum.INATIVO
        customerRepository.save(customer)
    }
}