package demo.kotlinpractice.domain.member.domain.repository

import demo.kotlinpractice.domain.member.domain.Member

interface MemberRepository {

    fun findById(id: Long): Member

    fun existsByName(name: String): Boolean

    fun save(member: Member): Member

    fun findByName(name: String): Member
}
