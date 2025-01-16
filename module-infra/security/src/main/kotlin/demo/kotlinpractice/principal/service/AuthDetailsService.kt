package demo.kotlinpractice.principal.service

@Service
class AuthDetailsService(
    private val memberRepository: MemberRepository
): UserDetailsService {
    override fun loadUserByUsername(name: String): AuthDetails {
        val member = memberRepository.findByName(name)
        return AuthDetails(member.getId(), member.getName(), member.getPassword(), "ROLE_USER")
    }
}