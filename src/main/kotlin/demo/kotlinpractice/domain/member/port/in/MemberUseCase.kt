package demo.kotlinpractice.domain.member.port.`in`

import demo.kotlinpractice.domain.member.domain.Member
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberCreateRequest
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberUpdateRequest

interface MemberUseCase {

    fun createMember(request: MemberCreateRequest): Member

    fun findMember(memberId: Long): Member

    fun updateMember(request: MemberUpdateRequest): Member
}