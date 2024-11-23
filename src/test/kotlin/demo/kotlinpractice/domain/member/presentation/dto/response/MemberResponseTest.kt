package demo.kotlinpractice.domain.member.presentation.dto.response

import demo.kotlinpractice.domain.member.domain.Member
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MemberResponseTest: StringSpec({
    "MemberResponseDTO는 Member을 변수로 받아 정상적으로 자신의 타입으로 변환해야 한다." {
        // Given
        val memberId = 1L
        val name = "jwnnoh"
        val password = "password"
        val member = Member(memberId, name, password)

        // When
        val response = MemberResponse.of(member)

        // Then
        response.id shouldBe memberId
        response.name shouldBe name
    }
})