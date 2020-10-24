package bancoDigital.nosso.exceptions

import java.lang.RuntimeException

class NameException(override val message: String): RuntimeException(message) {
}