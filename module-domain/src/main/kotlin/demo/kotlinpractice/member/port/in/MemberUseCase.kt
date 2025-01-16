package demo.kotlinpractice.member.port.`in`

import demo.kotlinpractice.member.domain.Member

interface MemberUseCase {
    fun createMember(name: String, password: String): Member

    fun findById(memberId: Long): Member

    fun findByName(name: String): Member?

    fun updateMember(
        memberId: Long,
        name: String,
        password: String,
    ): Member
}