package demo.kotlinpractice.member.domain

import demo.kotlinpractice.config.MemberFixtures.createMember
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MemberTest : DescribeSpec({
    describe("Member 도메인") {
        context("Member가 생성될 때") {
            it("get 메서드를 통해 올바른 필드 값을 반환해야 한다.") {
                val member = createMember()

                val newName = "jwnn0h"
                val newPassword = "newPassword"

                member.updateInfo(newName, newPassword).apply {
                    member.name.shouldBe(newName)
                    member.password.shouldBe(newPassword)
                }
            }
        }
    }
})
