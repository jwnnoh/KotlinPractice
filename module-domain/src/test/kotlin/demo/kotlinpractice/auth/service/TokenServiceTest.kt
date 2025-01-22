package demo.kotlinpractice.auth.service

import demo.kotlinpractice.auth.port.out.TokenPort
import demo.kotlinpractice.config.MemberFixtures.createMember
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class TokenServiceTest : DescribeSpec({
    lateinit var tokenPort: TokenPort
    lateinit var tokenService: TokenService

    describe("TokenService") {
        tokenPort = mockk()
        tokenService = TokenService(tokenPort)
        context("generateAccessToken() 메서드를 통해") {
            it("tokenPort를 통해 토큰을 String 형식으로 반환받아야 한다.") {
                val member = createMember()
                val id = member.id
                val name = member.name
                val role = "ROLE_USER"
                val accessToken = "accessToken"

                every {
                    tokenPort.generateAccessToken(
                        id,
                        name,
                        role
                    )
                } returns accessToken

                val result = tokenService.generateAccessToken(
                    id,
                    name,
                    role,
                )
                result shouldBe accessToken

                verify(exactly = 1) {
                    tokenPort.generateAccessToken(
                        match { it == id },
                        match { it == name },
                        match { it == role },
                    )
                }
                confirmVerified(tokenPort)
            }
        }
    }
})
