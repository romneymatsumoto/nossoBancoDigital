package bancoDigital.nosso.controller

import bancoDigital.nosso.model.customer.Owner
import bancoDigital.nosso.service.OwnerService
import org.springframework.http.ResponseEntity
import org.springframework.jca.cci.CannotCreateRecordException
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/owner")
class OwnerController(val ownerService: OwnerService) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Owner> =
            ownerService.findById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @GetMapping()
    fun findAll(): List<Owner>? = ownerService.findAll()

    @PostMapping()
    fun create(@RequestBody owner: Owner): ResponseEntity<Owner> {
        return try {
            val savedCustomer = ownerService.create(owner)
            val location: URI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/address").build(owner.id)
            return ResponseEntity.created(location).body(savedCustomer)
        } catch (e: CannotCreateRecordException) {
            ResponseEntity.badRequest().body(owner)
        }
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody owner: Owner): ResponseEntity<Owner> {
        return try {
            ownerService.update(id, owner)
            ResponseEntity.noContent().build()
        } catch (ex: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long, @RequestBody owner: Owner): ResponseEntity<Any> {
        return try {
            ownerService.delete(id)
            ResponseEntity.noContent().build()
        } catch (ex: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }
}