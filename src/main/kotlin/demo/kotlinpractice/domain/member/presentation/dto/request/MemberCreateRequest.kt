package demo.kotlinpractice.domain.member.presentation.dto.request

data class MemberCreateRequest(
    val name: String,
    val password: String,
)
