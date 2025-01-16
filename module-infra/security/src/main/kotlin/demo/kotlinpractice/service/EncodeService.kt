package demo.kotlinpractice.service

import demo.kotlinpractice.auth.port.out.SecurityPort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class EncodeService(
    private val passwordEncoder: PasswordEncoder,
) : SecurityPort {
    override fun encode(password: String): String =
        passwordEncoder.encode(password)
}
