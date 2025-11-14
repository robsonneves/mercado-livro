package com.mercadolivro.service

import org.springframework.data.repository.CrudRepository

interface GenericCrudService<T : Any, ID : Any> {

    fun repo(): CrudRepository<T, ID>
    fun findAll(): List<T> = repo().findAll().toList()
    fun findById(id: ID): T = repo().findById(id).orElseThrow { RuntimeException("Not found") }
    fun save(entity: T): T = repo().save(entity)
    fun delete(id: ID) = repo().deleteById(id)
}