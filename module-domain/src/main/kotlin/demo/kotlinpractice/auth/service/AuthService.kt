package demo.kotlinpractice.auth.service

import demo.kotlinpractice.auth.port.`in`.AuthUseCase
import demo.kotlinpractice.auth.port.out.AuthenticationPort
import demo.kotlinpractice.auth.port.out.SecurityPort
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val securityPort: SecurityPort,
    private val authenticationPort: AuthenticationPort,
) : AuthUseCase {
    override fun encodePassword(password: String): String =
        securityPort.encode(password)

    override fun loginMember(name: String, password: String): Long =
        authenticationPort.authenticate(name, password)
}
