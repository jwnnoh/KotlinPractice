package demo.kotlinpractice.member.service

import demo.kotlinpractice.member.domain.Member
import demo.kotlinpractice.member.port.`in`.MemberUseCase
import demo.kotlinpractice.member.port.out.MemberPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberPersistencePort: MemberPersistencePort,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationService: AuthenticationService
): MemberUseCase {
    @Transactional
    override fun createMember(request: MemberCreateRequest): Member {
        val member = Member(
            id = 0,
            name = request.name,
            password = passwordEncoder.encode(request.password)
        )
        return memberRepository.save(member)
    }

    @Transactional(readOnly = true)
    override fun findMember(memberId: Long): Member {
        return memberRepository.findById(memberId)
    }

    @Transactional
    override fun updateMember(request: MemberUpdateRequest): Member {
        val member = findMember(request.memberId)
        member.updateInfo(request.name, request.password)

        return memberRepository.save(member)
    }

    @Transactional
    override fun loginMember(request: LoginRequest): LoginResponse {
       return authenticationService.authenticate(request)
    }
}