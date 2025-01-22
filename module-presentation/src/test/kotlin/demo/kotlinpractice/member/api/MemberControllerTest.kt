package demo.kotlinpractice.member.api

import com.fasterxml.jackson.databind.ObjectMapper
import demo.kotlinpractice.member.dto.request.MemberUpdateRequest
import demo.kotlinpractice.member.dto.response.MemberResponse
import demo.kotlinpractice.member.facade.MemberFacade
import demo.kotlinpractice.type.SuccessCode
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class MemberControllerTest : DescribeSpec({
    val memberFacade = mockk<MemberFacade>()
    val memberController = MemberController(memberFacade)
    val mockMvc = MockMvcBuilders.standaloneSetup(memberController).build()

    describe("MemberController") {
        context("멤버 업데이트 API 호출 시") {
            val id = 1L
            val name = "jwnnoh"
            val password = "password"
            it("정상적으로 이름하고 비밀번호는 변경되어야 한다.") {
                val memberUpdateRequest = MemberUpdateRequest(
                    id,
                    name,
                    password,
                )

                val memberResponse = MemberResponse(id, name)

                every { memberFacade.updateMember(memberUpdateRequest) } returns memberResponse

                mockMvc.patch("/member") {
                    contentType = MediaType.APPLICATION_JSON
                    content = ObjectMapper().writeValueAsString(memberUpdateRequest)
                }.andExpect {
                    status { isOk() }
                    jsonPath("$.code") { value(SuccessCode.REQUEST_OK.code) }
                    jsonPath("$.result.id") { value(id) }
                    jsonPath("$.result.name") { value(name) }
                }
            }
        }
    }
})
