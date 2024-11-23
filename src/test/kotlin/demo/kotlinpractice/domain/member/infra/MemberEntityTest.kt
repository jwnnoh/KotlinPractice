package demo.kotlinpractice.domain.member.infra

import demo.kotlinpractice.domain.member.domain.Member
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MemberEntityTest: StringSpec({
    "toDomain 메서드를 통해 Member 객체를 반환할 수 있어야 한다." {
        // Given
        val id = 1L
        val name = "jwnnoh"
        val password = "password"

        // When
        val memberEntity = MemberEntity(id, name, password)
        val member = memberEntity.toDomain()

        // Then
        member.getId() shouldBe id
        member.getName() shouldBe name
        member.getPassword() shouldBe password
    }

    "from 메서드를 통해 Member 객체로부터 MemberEntity를 반환할 수 있어야 한다. " {
        // Given
        val id = 1L
        val name = "jwnnoh"
        val password = "password"

        // When
        val member = Member(id, name, password)
        val memberEntity = MemberEntity.from(member)

        // Then
        memberEntity.getId() shouldBe id
        memberEntity.getName() shouldBe name
        memberEntity.getPassword() shouldBe password
    }
})