package demo.kotlinpractice.domain.member.presentation.dto.request

data class MemberUpdateRequest(
    val memberId: Long,
    val name: String,
    val password: String
)