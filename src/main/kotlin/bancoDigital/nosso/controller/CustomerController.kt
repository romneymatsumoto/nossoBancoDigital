package bancoDigital.nosso.controller

import bancoDigital.nosso.model.Customer
import bancoDigital.nosso.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/customer")
class CustomerController(val customerService: CustomerService) {

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Customer> =
        customerService.findById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @GetMapping()
    fun findAll(): List<Customer>? = customerService.findAll()

    @PostMapping()
    fun create(@RequestBody customer: Customer): ResponseEntity<Customer> {
        val savedCustomer = customerService.create(customer)
        val location: URI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/customer/{id}").build(savedCustomer.id)
        return ResponseEntity.created(location).build()
    }
}