package bancoDigital.nosso.model

import bancoDigital.nosso.model.customer.Owner
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.validation.constraints.NotBlank

@Entity
data class Customer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        @NotBlank
        @OneToOne(cascade = [CascadeType.ALL])
        val owner: Owner
)