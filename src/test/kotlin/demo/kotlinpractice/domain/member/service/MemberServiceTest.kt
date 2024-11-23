package demo.kotlinpractice.domain.member.service

import demo.kotlinpractice.domain.member.domain.Member
import demo.kotlinpractice.domain.member.domain.repository.MemberRepository
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberCreateRequest
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberUpdateRequest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

class MemberServiceTest : StringSpec({

    "이름과 비밀번호를 통해 멤버가 정상적으로 생성되고 반환되어야 한다" {
        // Given
        val name = "jwnnoh"
        val password = "password"
        val request = MemberCreateRequest(name, password)

        // Mock
        val memberRepository: MemberRepository = mockk<MemberRepository>()
        val memberService = MemberService(memberRepository)

        val member = Member(1L, name, password)

        // Stub
        every { memberRepository.save(any()) } returns member

        // When
        val savedMember: Member = memberService.createMember(request)

        // Then
        savedMember.getId() shouldBe member.getId()
        savedMember.getName() shouldBe member.getName()
        savedMember.getPassword() shouldBe member.getPassword()

        // Verify & Confirm
        verify(exactly = 1) { memberRepository.save(any()) }
        confirmVerified(memberRepository)
    }

    "이름과 비밀번호는 updateInfo 메서드를 통해 정상적으로 수정되어야 한다." {
        // Given
        val memberId = 1L
        val newName = "newName"
        val newPassword = "newPassword"
        val member = Member(memberId, "jwnnoh", "password")
        val request = MemberUpdateRequest(memberId, newName, newPassword)

        // Mock
        val memberRepository: MemberRepository = mockk<MemberRepository>()
        val memberService = MemberService(memberRepository)

        // Stub
        every { memberRepository.findById(any()) } returns member
        every { memberRepository.save(any()) } returns member

        // When
        val updatedMember: Member = memberService.updateMember(request)

        // Then
        updatedMember.getName() shouldBe newName
        updatedMember.getPassword() shouldBe newPassword

        // Verify & Confirm
        verify(exactly = 1) { memberRepository.findById(any()) }
        verify(exactly = 1) { memberRepository.save(any()) }
        confirmVerified(memberRepository)
    }
})