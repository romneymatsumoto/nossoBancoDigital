package bancoDigital.nosso.exceptions

import java.lang.RuntimeException

class LastNameException(override val message: String): RuntimeException(message) {
}