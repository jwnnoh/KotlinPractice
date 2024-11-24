package demo.kotlinpractice.domain.auth

import demo.kotlinpractice.domain.member.domain.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val memberRepository: MemberRepository
): UserDetailsService {
    override fun loadUserByUsername(name: String): AuthDetails {
        val member = memberRepository.findByName(name)
        return AuthDetails(member.getId(), member.getName(), member.getPassword(), "ROLE_USER")
    }
}
