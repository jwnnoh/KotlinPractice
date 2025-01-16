package demo.kotlinpractice.member.repository

import demo.kotlinpractice.domain.member.domain.Member
import demo.kotlinpractice.domain.member.infra.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MemberJPARepository : JpaRepository<MemberEntity, Long> {

    fun existsByName(name: String): Boolean

    fun findByName(name: String): Optional<Member>
}