package bancoDigital.nosso.service.impl

import bancoDigital.nosso.exceptions.CPFException
import bancoDigital.nosso.exceptions.EmailException
import bancoDigital.nosso.exceptions.LastNameException
import bancoDigital.nosso.exceptions.NameException
import bancoDigital.nosso.exceptions.YearsException
import bancoDigital.nosso.model.customer.Owner
import bancoDigital.nosso.repositories.OwnerRepository
import bancoDigital.nosso.service.OwnerService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Period
import javax.swing.text.MaskFormatter

@Service
class OwnerServiceImp(val ownerRepository: OwnerRepository) : OwnerService {
    override fun findById(id: Long): Owner? = ownerRepository.findByIdOrNull(id)

    override fun findAll(): List<Owner>? = ownerRepository.findAll()

    override fun create(owner: Owner): Owner {
        owner.birthDate?.let {
            yearsValidator(it)
        } ?: throw YearsException("Age can not be null.")
        owner.name?.let { nameValidator(it) }
        owner.lastName?.let { lastNameValidator(it) }
        owner.email?.let { emailValidator(it) }
        if(owner.cpf?.let { cpfValidator(it) }!!) owner.cpf = owner.cpf?.let { correctCPF(it) }
        return ownerRepository.save(owner)
    }

    override fun update(id: Long, owner: Owner) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }

    private fun nameValidator(name: String): Boolean {
        if (name.isBlank()) throw NameException("Name can not be null or empty.")
        if (name.length < 2 || name.length > 25) throw NameException("The name must be between 2 and 25 characters.")
        return true
    }

    private fun lastNameValidator(lastName: String): Boolean {
        if (lastName.isBlank()) throw LastNameException("Last name can not be null or empty.")
        if (lastName.length > 50) throw LastNameException("Last name must be between 1 and 50 characters.")
        return true
    }

    private fun emailValidator(email: String): Boolean {
        if (email.isBlank()) throw  EmailException("E-mail can not be null or empty.")
        if (!ownerRepository.findByEmail(email).isNullOrEmpty()) throw EmailException("E-mail already registered.")
        return true
    }

    private fun cpfValidator(cpf: String): Boolean {
        if (!cpf.length.equals(11)) throw CPFException("CPF does not match the size.")
        if (!ownerRepository.findByCpf(correctCPF(cpf)).isNullOrEmpty()) throw CPFException("CPF already registered.")
        return true
    }

    private fun correctCPF(cpf: String): String {
        val mask: MaskFormatter = MaskFormatter("###.###.###-##")
        mask.setValueContainsLiteralCharacters(false)
        return mask.valueToString(cpf)
    }

    private fun yearsValidator(birthDate: LocalDate): Boolean {
    	val now: LocalDate = LocalDate.now()
    	val period: Period = Period.between(birthDate, now)
        if (period.years < 18) throw YearsException("Age less than allowed.")
        return true
    }
}