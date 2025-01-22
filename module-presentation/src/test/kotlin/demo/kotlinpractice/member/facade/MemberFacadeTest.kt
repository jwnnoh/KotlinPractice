package demo.kotlinpractice.member.facade

import demo.kotlinpractice.auth.port.`in`.AuthUseCase
import demo.kotlinpractice.member.domain.Member
import demo.kotlinpractice.member.dto.request.MemberUpdateRequest
import demo.kotlinpractice.member.dto.response.MemberResponse
import demo.kotlinpractice.member.port.`in`.MemberUseCase
import demo.kotlinpractice.principal.AuthDetails
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class MemberFacadeTest : DescribeSpec({
    val memberUseCase = mockk<MemberUseCase>()
    val authUseCase = mockk<AuthUseCase>()

    val memberFacade = MemberFacade(memberUseCase, authUseCase)

    describe("MemberFacade") {
        val id = 1L
        val name = "jwnnoh"
        val password = "password"
        val member = Member(
            id = id,
            name = name,
            password = password
        )

        context("findMember() 메서드에서 유효한 authDetails 객체가 전달되면") {
            it("인증 객체의 id를 가진 Member가 있을 경우 정상적으로 id와 name을 반환한다.") {
                val authDetails = mockk<AuthDetails>()
                every { authDetails.getId() } returns id
                every { memberUseCase.findById(id) } returns member

                val result: MemberResponse = memberFacade.findMember(authDetails)

                result.id shouldBe id
                result.name shouldBe name
            }
        }

        context("updateMember() 메서드에서 유효한 DTO가 전달되면") {
            it("새로운 비밀번호를 암호화하고, 이름을 변경한 뒤 MemberResponse DTO에 담아 반환한다.") {
                val updatedName = "updatedName"
                val updatedPassword = "updatedPassword"
                val encodedUpdatedPassword = "encodedUpdatedPassword"
                val memberUpdateRequest = MemberUpdateRequest(
                    memberId = id,
                    name = updatedName,
                    password = updatedPassword
                )
                every { authUseCase.encodePassword(updatedPassword) } returns encodedUpdatedPassword

                val updatedMember = Member(
                    id = id,
                    name = updatedName,
                    password = encodedUpdatedPassword
                )

                every {
                    memberUseCase.updateMember(
                        memberUpdateRequest.memberId,
                        memberUpdateRequest.name,
                        encodedUpdatedPassword
                    )
                } returns updatedMember

                val result = memberFacade.updateMember(memberUpdateRequest)

                result.id shouldBe id
                result.name shouldBe updatedName
            }
        }
    }
})
