package demo.kotlinpractice.member.repository

import demo.kotlinpractice.member.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MemberJPARepository : JpaRepository<MemberEntity, Long> {

    fun existsByName(name: String): Boolean

    fun findByName(name: String): Optional<MemberEntity>
}
