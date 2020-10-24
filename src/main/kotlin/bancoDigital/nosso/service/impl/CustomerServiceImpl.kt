package bancoDigital.nosso.service.impl

import bancoDigital.nosso.model.Customer
import bancoDigital.nosso.repositories.CustomerRepository
import bancoDigital.nosso.service.CustomerService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class CustomerServiceImpl(val customerRepository: CustomerRepository) : CustomerService {

    override fun findById(id: Long): Customer? = customerRepository.findByIdOrNull(id)

    override fun findAll(): List<Customer>? = customerRepository.findAll()

    override fun create(customer: Customer): Customer = customerRepository.save(customer)

    override fun update(id: Long, customer: Customer) {
        customerRepository.findByIdOrNull(id)?.let {
            customer.id = it.id
            customerRepository.save(customer)
        } ?: throw EntityNotFoundException("Id not found.")
    }

    override fun delete(id: Long) {
        customerRepository.findByIdOrNull(id)?.let {
            customerRepository.deleteById(id)
        } ?: throw EntityNotFoundException("Id not found.")
    }
}