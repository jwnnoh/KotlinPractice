package demo.kotlinpractice.domain.member.port.`in`

import demo.kotlinpractice.domain.member.domain.Member
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberCreateRequest

interface MemberUseCase {

    fun createMember(request: MemberCreateRequest): Member
}