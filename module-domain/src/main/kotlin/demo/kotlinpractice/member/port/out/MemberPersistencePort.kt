package demo.kotlinpractice.member.port.out

import demo.kotlinpractice.member.domain.Member
import demo.kotlinpractice.member.domain.MemberVO

interface MemberPersistencePort {
    fun findById(id: Long): Member?

    fun existsByName(name: String): Boolean

    fun save(member: Member): Member

    fun save(memberVO: MemberVO): Member

    fun findByName(name: String): Member?
}
