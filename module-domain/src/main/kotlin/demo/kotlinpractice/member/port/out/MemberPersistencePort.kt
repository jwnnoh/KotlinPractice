package demo.kotlinpractice.member.port.out

import demo.kotlinpractice.member.domain.Member

interface MemberPersistencePort {
    fun findById(id: Long): Member?

    fun existsByName(name: String): Boolean

    fun save(member: Member): Member

    fun findByName(name: String): Member?
}
