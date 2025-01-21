package demo.kotlinpractice.auth.service

import demo.kotlinpractice.auth.port.out.AuthenticationPort
import demo.kotlinpractice.auth.port.out.SecurityPort
import demo.kotlinpractice.config.MemberFixtures.createMember
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class AuthServiceTest : DescribeSpec({
    lateinit var authService: AuthService
    lateinit var securityPort: SecurityPort
    lateinit var authenticationPort: AuthenticationPort

    describe("AuthService") {
        securityPort = mockk()
        authenticationPort = mockk()
        authService = AuthService(securityPort, authenticationPort)

        context("encodePasssword() 메서드를 통해") {
            it("비밀번호가 정상적으로 암호화되어야 한다.") {
                val password = "password"
                val encodedPassword = "encodedPassword"

                every { securityPort.encode(password) } returns encodedPassword

                val result = authService.encodePassword(password)
                result shouldBe encodedPassword

                verify(exactly = 1) {
                    securityPort.encode(password)
                }
                confirmVerified(securityPort)
            }
        }

        context("loginMember() 메서드를 통해") {
            it("정상적으로 member의 id가 반환되어야 한다.") {
                val member = createMember()
                val name = member.name
                val password = member.password

                every {
                    authenticationPort.authenticate(name, password)
                } returns member.id

                val result = authService.loginMember(name, password)
                result shouldBe member.id

                verify(exactly = 1) {
                    authenticationPort.authenticate(name, password)
                }
                confirmVerified(authenticationPort)
            }
        }
    }
})
