package demo.kotlinpractice.member.facade

import demo.kotlinpractice.auth.port.`in`.AuthUseCase
import demo.kotlinpractice.member.domain.Member
import demo.kotlinpractice.member.dto.request.MemberUpdateRequest
import demo.kotlinpractice.member.dto.response.MemberResponse
import demo.kotlinpractice.member.port.`in`.MemberUseCase
import demo.kotlinpractice.principal.AuthDetails
import org.springframework.stereotype.Service

@Service
class MemberFacade(
    private val memberUseCase: MemberUseCase,
    private val authUseCase: AuthUseCase,
) {
    fun findMember(authDetails: AuthDetails): MemberResponse {
        val member: Member = memberUseCase.findById(authDetails.getId())

        return MemberResponse.of(member)
    }

    fun updateMember(request: MemberUpdateRequest): MemberResponse {

        val member: Member = memberUseCase.updateMember(
            request.memberId,
            request.name,
            authUseCase.encodePassword(request.password),
        )

        return MemberResponse.of(member)
    }
}
