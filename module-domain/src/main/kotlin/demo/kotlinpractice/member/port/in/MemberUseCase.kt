package demo.kotlinpractice.member.port.`in`

import demo.kotlinpractice.member.domain.Member
import demo.kotlinpractice.member.domain.MemberVO

interface MemberUseCase {
    fun createMember(memberVO: MemberVO): Member

    fun findById(memberId: Long): Member

    fun findByName(name: String): Member?

    fun updateMember(
        memberId: Long,
        name: String,
        password: String,
    ): Member
}