package demo.kotlinpractice.domain.member.presentation.dto.response

import demo.kotlinpractice.domain.member.domain.Member

data class MemberResponse(
    val id: Long,
    val name: String,
) {
    companion object {
        fun of(member: Member): MemberResponse {
            return MemberResponse(
                id = member.getId(),
                name = member.getName(),
            )
        }
    }
}
