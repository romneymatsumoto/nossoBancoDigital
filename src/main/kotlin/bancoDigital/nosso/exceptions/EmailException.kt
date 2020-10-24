package bancoDigital.nosso.exceptions

import java.lang.RuntimeException

class EmailException(override val message: String): RuntimeException(message) {
}