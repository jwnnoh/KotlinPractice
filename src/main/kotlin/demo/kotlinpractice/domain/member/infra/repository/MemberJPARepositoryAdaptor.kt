package demo.kotlinpractice.domain.member.infra.repository

import demo.kotlinpractice.domain.member.domain.Member
import demo.kotlinpractice.domain.member.domain.repository.MemberRepository
import demo.kotlinpractice.domain.member.infra.MemberEntity
import kotlin.jvm.optionals.getOrNull

class MemberJPARepositoryAdaptor(
    private val memberJPARepository: MemberJPARepository
) : MemberRepository {

    override fun findById(id: Long): Member {
        return memberJPARepository.findById(id).getOrNull()?.toDomain()
            ?: throw IllegalArgumentException()
    }

    override fun existsByName(name: String): Boolean {
        return memberJPARepository.existsByName(name)
    }

    override fun save(member: Member): Member {
        return memberJPARepository.save(MemberEntity.from(member)).toDomain()
    }
}