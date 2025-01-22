package demo.kotlinpractice.member.repository

import demo.kotlinpractice.config.MemberFixtures.createMemberVO
import demo.kotlinpractice.member.entity.MemberEntity
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberJPARepositoryAdaptorTest(
    @Autowired private val memberJPARepository: MemberJPARepository
) : DescribeSpec({
    val memberJPARepositoryAdaptor = MemberJPARepositoryAdaptor(memberJPARepository)
    val memberVO = createMemberVO()
    val memberName = memberVO.name
    val memberPassword = memberVO.password

    beforeSpec {

        memberJPARepository.save(
            MemberEntity.from(memberVO)
        )
    }

    afterSpec {
        memberJPARepository.deleteAllInBatch()
    }

    describe("MemberJPARepositoryAdaptor") {
        context("Member의 이름이 주어졌을 때 존재하면") {
            it("existsByName() 메서드가 true를 반환한다.") {
                val result = memberJPARepositoryAdaptor.existsByName(memberName)

                result shouldBe true
            }

            it("findByName() 메서드가 Member 도메인을 반환한다.") {
                val resultMember = memberJPARepositoryAdaptor.findByName(memberName)

                resultMember!!.apply {
                    name shouldBe memberName
                    password shouldBe memberPassword
                }
            }
        }

        context("Member의 이름이 주어졌을 때 존재하지 않으면") {
            val anotherMemberName = "anotherMemberName"
            it("existsByName() 메서드가 false를 반환한다.") {
                val result = memberJPARepositoryAdaptor.existsByName(anotherMemberName)

                result shouldBe false
            }

            it("findByName() 메서드가 Unit(Null)을 반환한다.") {
                val resultMember = memberJPARepositoryAdaptor.findByName(anotherMemberName)

                resultMember shouldBe null
            }
        }
    }
})
