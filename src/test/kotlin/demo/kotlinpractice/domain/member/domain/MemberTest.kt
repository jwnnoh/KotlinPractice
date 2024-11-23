package demo.kotlinpractice.domain.member.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MemberTest : StringSpec({
    "get 메서드를 통한 멤버 클래스 특성 조회는 올바른 값을 반환해야한다." {
        // Given
        val id = 1L
        val name = "jwnnoh"
        val password = "password"

        // When
        val member = Member(id, name, password)

        // Then
        member.getId() shouldBe id
        member.getName() shouldBe name
        member.getPassword() shouldBe password
    }
})
