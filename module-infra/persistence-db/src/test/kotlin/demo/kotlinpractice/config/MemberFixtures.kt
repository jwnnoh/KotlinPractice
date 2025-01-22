package demo.kotlinpractice.config

import demo.kotlinpractice.member.domain.MemberVO

object MemberFixtures {
    fun createMemberVO(
        name: String = "jwnnoh",
        password: String = "password",
    ): MemberVO = MemberVO(
        name = name,
        password = password
    )
}
