package demo.kotlinpractice.domain.member.infra.repository

import demo.kotlinpractice.domain.member.infra.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJPARepository : JpaRepository<MemberEntity, Long> {

    fun existsByName(name: String): Boolean
}