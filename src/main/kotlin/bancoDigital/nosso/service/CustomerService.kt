package bancoDigital.nosso.service

import bancoDigital.nosso.model.Customer

interface CustomerService {

    fun findById(id: Long): Customer?

    fun findAll(): List<Customer>?

    fun create(customer: Customer): Customer

    fun update(id: Long, customer: Customer)

    fun delete(id: Long)
}