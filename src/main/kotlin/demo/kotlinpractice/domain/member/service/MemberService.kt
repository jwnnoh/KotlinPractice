package demo.kotlinpractice.domain.member.service

import demo.kotlinpractice.domain.member.domain.Member
import demo.kotlinpractice.domain.member.domain.repository.MemberRepository
import demo.kotlinpractice.domain.member.port.`in`.MemberUseCase
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
): MemberUseCase {

    @Transactional
    override fun createMember(request: MemberCreateRequest): Member {
        val member = Member(
            id = 0,
            name = request.name,
            password = request.password
        )
        return memberRepository.save(member)
    }
}