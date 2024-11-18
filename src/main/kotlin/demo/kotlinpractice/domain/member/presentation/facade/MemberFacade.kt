package demo.kotlinpractice.domain.member.presentation.facade

import demo.kotlinpractice.domain.member.domain.Member
import demo.kotlinpractice.domain.member.port.`in`.MemberUseCase
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberCreateRequest
import demo.kotlinpractice.domain.member.presentation.dto.response.MemberResponse
import org.springframework.stereotype.Service

@Service
class MemberFacade(
    private final val memberUseCase: MemberUseCase
) {
    fun createMember(request: MemberCreateRequest): MemberResponse {
        val member: Member = memberUseCase.createMember(request)

        return MemberResponse.of(member)
    }
}