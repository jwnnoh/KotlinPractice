package demo.kotlinpractice.config

import demo.kotlinpractice.member.domain.Member

object MemberFixtures {
    fun createMember(
        id: Long = 1L,
        name: String = "jwnnoh",
        password: String = "password",
    ): Member = Member(
        id = id,
        name = name,
        password = password
    )
}