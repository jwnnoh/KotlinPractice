package demo.kotlinpractice.member.service

import demo.kotlinpractice.auth.port.out.SecurityPort
import demo.kotlinpractice.member.domain.Member
import demo.kotlinpractice.member.port.`in`.MemberUseCase
import demo.kotlinpractice.member.port.out.MemberPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberPersistencePort: MemberPersistencePort,
    private val securityPort: SecurityPort,
) : MemberUseCase {
    @Transactional
    override fun createMember(name: String, password: String): Member {
        val member = Member(
            id = 0,
            name = name,
            password = securityPort.encode(password)
        )
        return memberPersistencePort.save(member)
    }

    @Transactional(readOnly = true)
    override fun findById(memberId: Long): Member {
        return memberPersistencePort.findById(memberId)
    }

    @Transactional(readOnly = true)
    override fun findByName(name: String): Member? {
        return memberPersistencePort.findByName(name)
    }

    @Transactional
    override fun updateMember(
        memberId: Long,
        name: String,
        password: String,
    ): Member {
        val member = findById(memberId)
        member.updateInfo(name, password)

        return memberPersistencePort.save(member)
    }
}