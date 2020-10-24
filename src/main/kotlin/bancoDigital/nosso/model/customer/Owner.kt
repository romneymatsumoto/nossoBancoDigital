package bancoDigital.nosso.model.customer

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
data class Owner(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Size(min = 2, max = 25, message = "Name must be min 3 and max 25 characters.")
        @NotBlank(message = "Name can not be null.")
        val name: String? = null,

        @Size(min = 1, max = 50, message = "Last name must be min 1 and max 50 characters.")
        @NotBlank(message = "Last name can not be null.")
        val lastName: String? = null,

        @Email
        val email: String? = null,

        @NotBlank(message = "Name can not be null.")
        @JsonFormat(pattern = "dd/MM/yyyy")
        val birthDate: LocalDate? = null,

        @CPF
        @Size(min = 11, max = 11, message = "Valid CPF must have 11 numbers.")
        var cpf: String? = null
)