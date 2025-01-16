package demo.kotlinpractice.member.repository

import demo.kotlinpractice.domain.member.domain.Member
import demo.kotlinpractice.domain.member.domain.repository.MemberRepository
import demo.kotlinpractice.domain.member.infra.MemberEntity
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
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

    override fun findByName(name: String): Member {
        return memberJPARepository.findByName(name).getOrNull()
            ?: throw IllegalArgumentException()
    }
}