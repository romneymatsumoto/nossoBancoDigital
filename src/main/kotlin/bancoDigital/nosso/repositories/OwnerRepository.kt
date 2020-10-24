package bancoDigital.nosso.repositories

import bancoDigital.nosso.model.customer.Owner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OwnerRepository : JpaRepository<Owner, Long> {

    fun findByEmail(email: String): List<Owner>?

    fun findByCpf(cpf: String): List<Owner>?
}