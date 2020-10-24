package bancoDigital.nosso.service

import bancoDigital.nosso.model.customer.Owner

interface OwnerService {

    fun findById(id: Long): Owner?

    fun findAll(): List<Owner>?

    fun create(owner: Owner): Owner

    fun update(id: Long, owner: Owner)

    fun delete(id: Long)
}