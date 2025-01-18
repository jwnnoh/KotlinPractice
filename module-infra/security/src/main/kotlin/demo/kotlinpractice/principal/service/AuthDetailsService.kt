package demo.kotlinpractice.principal.service

import demo.kotlinpractice.error.exception.MemberNotFoundException
import demo.kotlinpractice.member.port.`in`.MemberUseCase
import demo.kotlinpractice.principal.AuthDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val memberUseCase: MemberUseCase
) : UserDetailsService {
    override fun loadUserByUsername(name: String): AuthDetails {
        val member = memberUseCase.findByName(name)
            ?: throw MemberNotFoundException()

        return AuthDetails(
            member.id,
            member.name,
            member.password,
            "ROLE_USER"
        )
    }
}
