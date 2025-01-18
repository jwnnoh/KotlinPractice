package demo.kotlinpractice.member.dto.request

data class MemberUpdateRequest(
    val memberId: Long,
    val name: String,
    val password: String
)
