package demo.kotlinpractice.domain.member.service

import demo.kotlinpractice.domain.member.domain.Member
import demo.kotlinpractice.domain.member.domain.repository.MemberRepository
import demo.kotlinpractice.domain.member.port.`in`.MemberUseCase
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberCreateRequest
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberUpdateRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
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
}
