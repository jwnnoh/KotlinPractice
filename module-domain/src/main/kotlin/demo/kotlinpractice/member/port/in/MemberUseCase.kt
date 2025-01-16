package demo.kotlinpractice.member.port.`in`

import demo.kotlinpractice.member.domain.Member

interface MemberUseCase {
    fun createMember(request: MemberCreateRequest): Member

    fun findMember(memberId: Long): Member

    fun updateMember(request: MemberUpdateRequest): Member

    fun loginMember(request: LoginRequest): LoginResponse
}