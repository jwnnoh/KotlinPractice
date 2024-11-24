package demo.kotlinpractice.domain.member.presentation.facade

import demo.kotlinpractice.domain.member.domain.Member
import demo.kotlinpractice.domain.member.port.`in`.MemberUseCase
import demo.kotlinpractice.domain.member.presentation.dto.request.LoginRequest
import demo.kotlinpractice.domain.member.presentation.dto.response.LoginResponse
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberCreateRequest
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberUpdateRequest
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

    fun findMember(memberId: Long): MemberResponse {
        val member: Member = memberUseCase.findMember(memberId)

        return MemberResponse.of(member)
    }

    fun updateMember(request: MemberUpdateRequest): MemberResponse {
        val member: Member = memberUseCase.updateMember(request)
        return MemberResponse.of(member)
    }

    fun loginMember(request: LoginRequest): LoginResponse {
        return memberUseCase.loginMember(request)
    }
}