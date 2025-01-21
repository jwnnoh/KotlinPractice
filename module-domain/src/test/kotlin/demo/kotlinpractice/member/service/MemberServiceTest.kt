package demo.kotlinpractice.member.service

import demo.kotlinpractice.config.MemberFixtures.createMember
import demo.kotlinpractice.error.exception.MemberNotFoundException
import demo.kotlinpractice.member.port.out.MemberPersistencePort
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class MemberServiceTest : DescribeSpec({
    lateinit var memberPersistencePort: MemberPersistencePort
    lateinit var memberService: MemberService

    describe("MemberService") {
        memberPersistencePort = mockk()
        memberService = MemberService(memberPersistencePort)

        val member = createMember()
        val id = member.id
        val name = member.name
        val password = member.password

        context("createMember() 메서드를 통해서") {
            it("MemberPersistencePort.save() 메서드가 정상적으로 호출되어, Member가 반환되어야 한다.") {
                every { memberPersistencePort.save(any()) } returns member

                val result = memberService.createMember(
                    name,
                    password
                )
                result shouldBe member

                verify(exactly = 1) {
                    memberPersistencePort.save(match {
                        it.name == name
                        it.password == password
                    })
                }
                confirmVerified(memberPersistencePort)
            }
        }

        context("findById() 메서드를 통해서") {
            it("Member를 찾을 수 있으면, 해당 Member를 반환한다") {
                every { memberPersistencePort.findById(any()) } returns member

                val result = memberService.findById(id)
                result shouldBe member

                verify(exactly = 1) {
                    memberPersistencePort.findById(id)
                }
                confirmVerified(memberPersistencePort)
            }

            it("해당 socialId로 Member를 찾을 수 없으면, null을 반환한다") {
                val invalidId = 2L
                every { memberPersistencePort.findById(invalidId) } returns null

                shouldThrow<MemberNotFoundException> {
                    memberService.findById(invalidId)
                }

                verify(exactly = 1) {
                    memberPersistencePort.findById(invalidId)
                }
                confirmVerified(memberPersistencePort)
            }
        }
    }
})
