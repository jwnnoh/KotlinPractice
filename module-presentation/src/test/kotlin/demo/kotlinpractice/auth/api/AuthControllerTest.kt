package demo.kotlinpractice.auth.api

import com.fasterxml.jackson.databind.ObjectMapper
import demo.kotlinpractice.auth.dto.request.LoginRequest
import demo.kotlinpractice.auth.dto.request.MemberCreateRequest
import demo.kotlinpractice.auth.dto.response.LoginResponse
import demo.kotlinpractice.auth.facade.AuthFacade
import demo.kotlinpractice.member.dto.response.MemberResponse
import demo.kotlinpractice.type.SuccessCode
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class AuthControllerTest : DescribeSpec({
    val authFacade = mockk<AuthFacade>()
    val authController = AuthController(authFacade)
    val mockMvc = MockMvcBuilders.standaloneSetup(authController).build()

    describe("AuthController") {
        val id = 1L
        val name = "jwnnoh"
        val password = "password"

        context("회원가입 API 호출 시") {
            it("id하고 name을 정상적으로 반환한다.") {
                val memberCreateRequest = MemberCreateRequest(name, password)
                val memberResponse = MemberResponse(id, name)

                every { authFacade.createMember(memberCreateRequest) } returns memberResponse

                mockMvc.post("/register") {
                    contentType = MediaType.APPLICATION_JSON
                    content = ObjectMapper().writeValueAsString(memberCreateRequest)
                }.andExpect {
                    status { isOk() }
                    jsonPath("$.code") { value(SuccessCode.REQUEST_OK.code) }
                    jsonPath("$.result.id") { value(id) }
                    jsonPath("$.result.name") { value(name) }
                }
            }
        }

        context("로그인 API 호출 시") {
            it("id하고 accessToken을 정상적으로 반환한다.") {
                val accessToken = "accessToken"

                val loginRequest = LoginRequest(name, password)
                val loginResponse = LoginResponse(id, accessToken)

                every { authFacade.loginMember(loginRequest) } returns loginResponse

                mockMvc.post("/login") {
                    contentType = MediaType.APPLICATION_JSON
                    content = ObjectMapper().writeValueAsString(loginRequest)
                }.andExpect {
                    status { isOk() }
                    jsonPath("$.code") { value(SuccessCode.LOGIN_SUCCESS.code) }
                    jsonPath("$.result.memberId") { value(id) }
                    jsonPath("$.result.accessToken") { value(accessToken) }
                }
            }
        }
    }
})
