package demo.kotlinpractice.member.dto.response

import demo.kotlinpractice.member.domain.Member

data class MemberResponse(
    val id: Long,
    val name: String,
) {
    companion object {
        fun of(member: Member): MemberResponse {
            return MemberResponse(
                id = member.id,
                name = member.name,
            )
        }
    }
}
