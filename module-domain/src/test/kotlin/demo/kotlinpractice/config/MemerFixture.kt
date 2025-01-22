package demo.kotlinpractice.config

import demo.kotlinpractice.member.domain.Member
import demo.kotlinpractice.member.domain.MemberVO

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

    fun createMemberVO(
        name: String = "jwnnoh",
        password: String = "password",
    ): MemberVO = MemberVO(
        name = name,
        password = password
    )
}