package demo.kotlinpractice.auth.facade

import demo.kotlinpractice.auth.dto.request.LoginRequest
import demo.kotlinpractice.auth.dto.request.MemberCreateRequest
import demo.kotlinpractice.auth.dto.response.LoginResponse
import demo.kotlinpractice.auth.port.`in`.AuthUseCase
import demo.kotlinpractice.auth.port.`in`.TokenUseCase
import demo.kotlinpractice.member.domain.Member
import demo.kotlinpractice.member.domain.MemberVO
import demo.kotlinpractice.member.dto.response.MemberResponse
import demo.kotlinpractice.member.port.`in`.MemberUseCase
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AuthFacadeTest : DescribeSpec({
    val authUseCase = mockk<AuthUseCase>()
    val memberUseCase = mockk<MemberUseCase>()
    val tokenUseCase = mockk<TokenUseCase>()

    val authFacade = AuthFacade(
        authUseCase,
        memberUseCase,
        tokenUseCase
    )

    describe("AuthFacade") {
        val id = 1L
        val name = "jwnnoh"
        val password = "password"
        val encodedPassword = "encodedPassword"
        context("createMember() 메서드에서 유효한 DTO가 주어질 때,") {
            it("정상적으로 비밀번호를 암호화하고 멤버를 생성하여 MemberResponse를 반환한다.") {
                val request = MemberCreateRequest(name, password)

                every { authUseCase.encodePassword(password) } returns encodedPassword

                val savedMember = Member(
                    id = id,
                    name = name,
                    password = encodedPassword
                )
                every {
                    memberUseCase.createMember(
                        MemberVO(name, encodedPassword)
                    )
                } returns savedMember

                val result = authFacade.createMember(request)

                result shouldBe MemberResponse(id, name)
            }
        }

        context("loginMember() 메서드에서 유효한 DTO가 주어질 때") {
            it("id와 토큰을 담은 LoginResponse를 반환한다.") {
                val accessToken = "accessToken"
                val request = LoginRequest(name, password)

                every {
                    authUseCase.loginMember(
                        request.name,
                        request.password
                    )
                } returns 1L

                val foundMember = Member(
                    id = id,
                    name = name,
                    password = encodedPassword
                )
                every { memberUseCase.findById(id) } returns foundMember

                every {
                    tokenUseCase.generateAccessToken(
                        id,
                        name,
                        "ROLE_USER"
                    )
                } returns accessToken

                val result = authFacade.loginMember(request)

                result shouldBe LoginResponse(id, accessToken)
            }
        }
    }
})
