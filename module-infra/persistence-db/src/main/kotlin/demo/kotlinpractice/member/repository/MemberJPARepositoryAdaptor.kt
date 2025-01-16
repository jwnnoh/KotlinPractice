package demo.kotlinpractice.member.repository

import demo.kotlinpractice.error.exception.MemberNotFoundException
import demo.kotlinpractice.member.domain.Member
import demo.kotlinpractice.member.entity.MemberEntity
import demo.kotlinpractice.member.port.out.MemberPersistencePort
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class MemberJPARepositoryAdaptor(
    private val memberJPARepository: MemberJPARepository
) : MemberPersistencePort {

    override fun findById(id: Long): Member =
        memberJPARepository.findById(id)
            .getOrNull()?.toDomain()
            ?: throw MemberNotFoundException()

    override fun existsByName(name: String): Boolean =
        memberJPARepository.existsByName(name)

    override fun save(member: Member): Member =
        memberJPARepository.save(
            MemberEntity.from(member)
        ).toDomain()

    override fun findByName(name: String): Member =
        memberJPARepository.findByName(name)
            .getOrNull()?.toDomain()
            ?: throw MemberNotFoundException()
}
