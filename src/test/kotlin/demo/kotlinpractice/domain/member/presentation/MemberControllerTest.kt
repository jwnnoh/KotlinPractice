package demo.kotlinpractice.domain.member.presentation

import demo.kotlinpractice.domain.member.presentation.dto.request.MemberCreateRequest
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberUpdateRequest
import demo.kotlinpractice.domain.member.presentation.dto.response.MemberResponse
import demo.kotlinpractice.domain.member.presentation.facade.MemberFacade
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.http.ResponseEntity

class MemberControllerTest : StringSpec({
    "DTO를 통한 멤버 생성의 반환은 정상적으로 이루어져야 한다." {
        // Given
        val name = "jwnnoh"
        val password = "password"
        val request = MemberCreateRequest(name, password)
        val response = MemberResponse(1L, name)

        // Mock
        val memberFacade: MemberFacade = mockk<MemberFacade>()
        val memberController = MemberController(memberFacade)

        // Stub
        every { memberFacade.createMember(any()) } returns response

        // When
        val httpResponse: ResponseEntity<MemberResponse> = memberController.createMember(request)

        // Then
        httpResponse.body?.id shouldBe 1L
        httpResponse.body?.name shouldBe name

        // Verify & Confirm
        verify(exactly = 1) { memberFacade.createMember(any()) }
        confirmVerified(memberFacade)
    }

    "memberId를 통한 Member 조회는 정상적으로 이루어져야 한다." {
        // Given
        val memberId = 1L
        val name = "jwnnoh"
        val response = MemberResponse(memberId, name)

        // Mock
        val memberFacade = mockk<MemberFacade>()
        val memberController = MemberController(memberFacade)

        // Stub
        every { memberFacade.findMember(any()) } returns response

        // When
        val httpResponse: ResponseEntity<MemberResponse> = memberController.findMember(memberId)

        // Then
        httpResponse.body?.id shouldBe 1L
        httpResponse.body?.name shouldBe name

        // Verify & Confirm
        verify(exactly = 1) { memberFacade.findMember(any()) }
        confirmVerified(memberFacade)
    }

    "name, password를 통한 수정된 멤버의 반환은 정상적으로 이루어져야 한다." {
        // Given
        val memberId = 1L
        val name = "jwnnoh"
        val password = "password"
        val request = MemberUpdateRequest(memberId, name, password)
        val response = MemberResponse(memberId, name)

        // Mock
        val memberFacade = mockk< MemberFacade>()
        val memberController = MemberController(memberFacade)

        // Stub
        every { memberFacade.updateMember(any()) } returns response

        // When
        val httpResponse: ResponseEntity<MemberResponse> = memberController.updateMember(request)

        // Then
        httpResponse.body?.id shouldBe 1L
        httpResponse.body?.name shouldBe name

        // Verify & Confirm
        verify(exactly = 1) { memberFacade.updateMember(any()) }
        confirmVerified(memberFacade)
    }
})